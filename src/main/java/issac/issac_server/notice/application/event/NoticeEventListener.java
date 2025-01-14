package issac.issac_server.notice.application.event;

import issac.issac_server.notice.application.NoticeFinder;
import issac.issac_server.notice.domain.Notice;
import issac.issac_server.reaction.domain.TargetType;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoticeEventListener {

    private final NoticeFinder noticeFinder;
    private final JobLauncher jobLauncher;
    private final JobRegistry jobRegistry;

    @Async
    @EventListener
    public void triggerNotificationJob(NoticeSaveEvent event) throws Exception{

        Notice notice = noticeFinder.findNotice(event.getNoticeId());

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("id", notice.getId())
                .addString("university",notice.getUniversity().toString())
                .addString("source",notice.getSource().toString())
                .addString("title",notice.getTitle())
                .addString("content",notice.getContent())
                .addString("entityType", TargetType.NOTICE.toString())
                .toJobParameters();

        // 키워드 알림
        jobLauncher.run(jobRegistry.getJob("keywordJob"), jobParameters);

        // 북마크 알림
        jobLauncher.run(jobRegistry.getJob("bookmarkJob"), jobParameters);

    }

}
