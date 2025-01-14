package issac.issac_server.notification.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import issac.issac_server.notification.domain.Notification;
import issac.issac_server.notification.domain.NotificationType;
import issac.issac_server.reaction.domain.TargetType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static issac.issac_server.common.config.Constant.FORMAT_LOCAL_DATE_TIME;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {

    private Long id;

    private NotificationType notificationType;

    private String title; // 키워드나 북마크 이름 또는 게시글 제목

    private String content; // 글 제목 및 댓글 내용

    private TargetType entityType;

    private String entityId;

    private String author;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FORMAT_LOCAL_DATE_TIME)
    private LocalDateTime createdAt;

    private boolean read;

    public static NotificationResponse from(Notification notification) {
        return new NotificationResponse(
                notification.getId(),
                notification.getNotificationType(),
                notification.getTitle(),
                notification.getContent(),
                notification.getEntityType(),
                notification.getEntityId(),
                notification.getAuthor(),
                notification.getCreatedDateTime(),
                notification.isReadStatus()
        );
    }
}
