package issac.issac_server.batch.application;

import issac.issac_server.batch.application.dto.KeywordQueueRequest;
import issac.issac_server.keyword.application.KeywordFinder;
import issac.issac_server.notice.domain.NoticeSource;
import issac.issac_server.notification.application.dto.NotificationRequest;
import issac.issac_server.notification.domain.NotificationType;
import issac.issac_server.reaction.domain.TargetType;
import issac.issac_server.user.domain.University;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@StepScope
public class KeywordJobProcessor implements ItemProcessor<String, KeywordQueueRequest> {

    private final KeywordFinder keywordFinder;
    private final String entityId;
    private final TargetType entityType;
    private final String title;
    private final String content;
    private final String author;
    private final NoticeSource source;
    private final University university;

    public KeywordJobProcessor(
            @Value("#{jobParameters['entityId']}") String entityId,
            @Value("#{jobParameters['entityType']}") String entityType,
            @Value("#{jobParameters['title']}") String title,
            @Value("#{jobParameters['content']}") String content,
            @Value("#{jobParameters['author']}") String author,
            @Value("#{jobParameters['university']}") String university,
            @Value("#{jobParameters['source']}") String source,
            KeywordFinder keywordFinder
    ) {
        this.entityId = entityId;
        this.entityType = TargetType.valueOf(entityType);
        this.title = title;
        this.content = content;
        this.author = author;
        this.source = NoticeSource.valueOf(source);
        this.university = University.valueOf(university);
        this.keywordFinder = keywordFinder;
    }

    @Override
    public KeywordQueueRequest process(String keyword) throws Exception {
        if (!(title.contains(keyword) || content.contains(keyword))) {
            return null;
        }

        List<Long> userIds = keywordFinder.findUserIdsByUniversityAndText(university, keyword);
        String notificationKeyword = entityType.equals(TargetType.NOTICE) ? keyword + '@' + source : keyword;

        return new KeywordQueueRequest(
                userIds,
                new NotificationRequest(
                        NotificationType.KEYWORD,
                        notificationKeyword,
                        title,
                        entityType,
                        entityId,
                        author
                )
        );
    }


}
