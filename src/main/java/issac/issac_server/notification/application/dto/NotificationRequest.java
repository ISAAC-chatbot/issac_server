package issac.issac_server.notification.application.dto;

import issac.issac_server.notification.domain.NotificationType;
import issac.issac_server.reaction.domain.TargetType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotificationRequest {

    private NotificationType notificationType;

    private String title; // 키워드나 북마크 이름 또는 게시글 제목

    private String content; // 글 제목 및 댓글 내용

    private TargetType entityType;

    private String entityId;

    private String author;
}
