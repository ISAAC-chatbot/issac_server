package issac.issac_server.notification.domain;

import issac.issac_server.common.domain.BaseTimeEntity;
import issac.issac_server.notification.application.dto.NotificationRequest;
import issac.issac_server.notification.exception.NotificationErrorCode;
import issac.issac_server.notification.exception.NotificationException;
import issac.issac_server.reaction.domain.TargetType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AllArgsConstructor
@Builder
@Table(name = "notification")
public class Notification extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType notificationType;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private TargetType entityType;

    @Column(nullable = false)
    private String entityId;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private boolean readStatus;

    public void markAsRead(Long userId) {
        if (!this.userId.equals(userId)) {
            throw new NotificationException(NotificationErrorCode.USER_IS_NOT_AUTHOR);
        }
        this.readStatus = true;
    }

    public static Notification from(Long userId, NotificationRequest request){
        return Notification.builder()
                .userId(userId)
                .notificationType(request.getNotificationType())
                .title(request.getTitle())
                .content(request.getContent())
                .entityType(request.getEntityType())
                .entityId(request.getEntityId())
                .author(request.getAuthor())
                .readStatus(false)
                .build();
    }
}
