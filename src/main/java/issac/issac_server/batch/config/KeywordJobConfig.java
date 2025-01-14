package issac.issac_server.batch.config;

import issac.issac_server.batch.application.KeywordMatchingProcessor;
import issac.issac_server.batch.application.RabbitMQKeywordWriter;
import issac.issac_server.batch.application.dto.RabbitMQResponse;
import issac.issac_server.keyword.domain.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KeywordJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final KeywordRepository keywordRepository;
    private final KeywordMatchingProcessor keywordMatchingProcessor;
    private final RabbitMQKeywordWriter rabbitMQKeywordWriter;

    private final int chunkSize = 10;

    @Bean
    public Job keywordJob() {
        return new JobBuilder("keywordJob", jobRepository)
                .start(keywordNotificationStep(null))
                .build();
    }

    @Bean
    @JobScope
    public Step keywordNotificationStep(@Value("#{jobParameters['university']}") String university) {
        return new StepBuilder("keywordNotificationStep", jobRepository)
                .<String, RabbitMQResponse>chunk(chunkSize, platformTransactionManager)
                .reader(keywordReader(university))
                .processor(keywordMatchingProcessor)
                .writer(rabbitMQKeywordWriter)
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean(name = "batchTaskExecutor")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(4);
        taskExecutor.setMaxPoolSize(4);
        taskExecutor.setQueueCapacity(10);
        taskExecutor.setThreadNamePrefix("keyword-batch-thread-");
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Bean
    @StepScope
    public RepositoryItemReader<String> keywordReader(@Value("#{jobParameters['university']}") String university) {
        return new RepositoryItemReaderBuilder<String>()
                .repository(keywordRepository)
                .methodName("findDistinctTextsByUniversity")
                .arguments(university)
                .pageSize(chunkSize)
                .maxItemCount(chunkSize)
                .sorts(Map.of("id", Sort.Direction.ASC))
                .name("keywordReader")
                .build();
    }


}
