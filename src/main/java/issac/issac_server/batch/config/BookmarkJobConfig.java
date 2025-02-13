package issac.issac_server.batch.config;

import issac.issac_server.batch.application.BookmarkJobProcessor;
import issac.issac_server.batch.application.BookmarkJobWriter;
import issac.issac_server.batch.application.dto.BookmarkQueueRequest;
import issac.issac_server.notice.domain.Bookmark;
import issac.issac_server.notice.domain.BookmarkRepository;
import issac.issac_server.notice.domain.NoticeSource;
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
public class BookmarkJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final BookmarkRepository bookmarkRepository;
    private final BookmarkJobProcessor bookmarkJobProcessor;
    private final BookmarkJobWriter bookmarkJobWriter;

    private final int chunkSize = 100;

    @Bean
    public Job bookmarkJob() {
        return new JobBuilder("bookmarkJob", jobRepository)
                .start(bookmarkNotificationStep(null))
                .build();
    }

    @Bean
    @JobScope
    public Step bookmarkNotificationStep(@Value("#{jobParameters['source']}") String source) {
        return new StepBuilder("bookmarkNotificationStep", jobRepository)
                .<Bookmark, BookmarkQueueRequest>chunk(chunkSize, platformTransactionManager)
                .reader(bookmarkReader(source))
                .processor(bookmarkJobProcessor)
                .writer(bookmarkJobWriter)
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean(name = "bookmarkBatchTaskExecutor")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
//        int coreCount = Runtime.getRuntime().availableProcessors();
        taskExecutor.setCorePoolSize(64); // 코어 수 기반 설정
        taskExecutor.setMaxPoolSize(64);
        taskExecutor.setQueueCapacity(1000); // 대기열 크기 증가
        taskExecutor.setThreadNamePrefix("bookmark-batch-thread-");
        taskExecutor.setAllowCoreThreadTimeOut(true);
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setAwaitTerminationSeconds(10);
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Bean
    @StepScope
    public RepositoryItemReader<Bookmark> bookmarkReader(@Value("#{jobParameters['source']}") String source) {
        return new RepositoryItemReaderBuilder<Bookmark>()
                .repository(bookmarkRepository)
                .methodName("findBySourceAndNotificationConsent")
                .arguments(NoticeSource.valueOf(source), true)
                .pageSize(chunkSize)
                .maxItemCount(chunkSize)
                .sorts(Map.of("id", Sort.Direction.ASC))
                .name("bookmarkReader")
                .build();
    }


}
