package issac.issac_server.batch.application;

import issac.issac_server.batch.application.dto.BookmarkQueueRequest;
import issac.issac_server.notice.domain.Bookmark;
import issac.issac_server.notification.application.dto.NotificationRequest;
import issac.issac_server.notification.domain.NotificationType;
import issac.issac_server.reaction.domain.TargetType;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class BookmarkJobProcessor implements ItemProcessor<Bookmark, BookmarkQueueRequest> {

    private final String id;
    private final String entityType;
    private final String title;
    private final String author;

    public BookmarkJobProcessor(
            @Value("#{jobParameters['id']}") String id,
            @Value("#{jobParameters['entityType']}") String entityType,
            @Value("#{jobParameters['title']}") String title,
            @Value("#{jobParameters['author']}") String author
    ) {
        this.id = id;
        this.entityType = entityType;
        this.title = title;
        this.author = author;
    }

    @Override
    public BookmarkQueueRequest process(Bookmark bookmark) throws Exception {

        return new BookmarkQueueRequest(
                bookmark.getUserId(),
                new NotificationRequest(
                        NotificationType.BOOKMARK,
                        bookmark.getSource().toString(),
                        title,
                        TargetType.valueOf(entityType),
                        id,
                        author
                )
        );
    }

}
