package issac.issac_server.notice.application.event;

import issac.issac_server.notice.application.NoticeFinder;
import issac.issac_server.notice.application.dto.request.NoticeCreateRequest;
import issac.issac_server.reaction.domain.TargetType;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class NoticeEventListener {

    private final NoticeFinder noticeFinder;
    private final JobLauncher jobLauncher;
    private final JobRegistry jobRegistry;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void triggerNotificationJob(NoticeSaveEvent event) throws Exception{

        NoticeCreateRequest request = event.getRequest();

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("entityId", event.getNoticeId())
                .addString("university",request.getUniversity().toString())
                .addString("source",request.getSource().toString())
                .addString("title",request.getTitle())
                .addString("content",request.getContent())
                .addString("entityType", TargetType.NOTICE.toString())
                .addString("author",request.getAuthor())
                .toJobParameters();

        // 키워드 알림 비동기 실행
        runJobAsync("keywordJob", jobParameters);

        // 북마크 알림 비동기 실행
        runJobAsync("bookmarkJob", jobParameters);

    }

    @Async
    public void runJobAsync(String jobName, JobParameters jobParameters) {
        try {
            jobLauncher.run(jobRegistry.getJob(jobName), jobParameters);
        } catch (Exception e) {
            // 예외 처리 로직
            e.printStackTrace();
        }
    }

}

