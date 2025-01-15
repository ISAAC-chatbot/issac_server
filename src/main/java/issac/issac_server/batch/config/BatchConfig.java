package issac.issac_server.batch.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final DataSource metaDBSource;
    private final PlatformTransactionManager metaTransactionManager;

    public BatchConfig(
            @Qualifier("metaDBSource") DataSource metaDBSource,
            @Qualifier("metaTransactionManager") PlatformTransactionManager metaTransactionManager) {
        this.metaDBSource = metaDBSource;
        this.metaTransactionManager = metaTransactionManager;
    }

    @Bean
    public JobRepository jobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(metaDBSource);
        factory.setTransactionManager(metaTransactionManager);
        factory.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
//        factory.setTablePrefix("BATCH_");
        factory.afterPropertiesSet();
        return factory.getObject();
    }

}
