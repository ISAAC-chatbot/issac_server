package issac.issac_server.batch.application;

import issac.issac_server.batch.application.dto.KeywordQueueRequest;
import issac.issac_server.keyword.application.KeywordFinder;
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
    private final String entityType;
    private final String title;
    private final String content;
    private final String author;
    private final String university;

    public KeywordJobProcessor(
            @Value("#{jobParameters['entityId']}") String entityId,
            @Value("#{jobParameters['entityType']}") String entityType,
            @Value("#{jobParameters['title']}") String title,
            @Value("#{jobParameters['content']}") String content,
            @Value("#{jobParameters['author']}") String author,
            @Value("#{jobParameters['university']}") String university,
            KeywordFinder keywordFinder
    ) {
        this.entityId = entityId;
        this.entityType = entityType;
        this.title = title;
        this.content = content;
        this.author = author;
        this.university = university;
        this.keywordFinder = keywordFinder;
    }

    @Override
    public KeywordQueueRequest process(String keyword) throws Exception {

        if (title.contains(keyword) || content.contains(keyword)) {

            List<Long> userIds = keywordFinder.findUserIdsByUniversityAndText(University.valueOf(university), keyword);

            return new KeywordQueueRequest(
                    userIds,
                    new NotificationRequest(
                            NotificationType.KEYWORD,
                            keyword,
                            title,
                            TargetType.valueOf(entityType),
                            entityId,
                            author
                    )
            );
        }

        return null;
    }

}
