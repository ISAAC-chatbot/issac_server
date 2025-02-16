package issac.issac_server.notification.application.dto;

import issac.issac_server.comment.domain.Comment;
import issac.issac_server.notification.domain.NotificationType;
import issac.issac_server.post.domain.Post;
import issac.issac_server.reaction.domain.TargetType;
import issac.issac_server.report.domain.Report;
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


    public static NotificationRequest of(Post post, Comment comment) {
        return new NotificationRequest(
                NotificationType.COMMENT,
                post.getTitle(),
                comment.getContent(),
                TargetType.COMMENT,
                comment.getId().toString(),
                comment.getAuthor().getProfile().getNickname()
        );
    }

    public static NotificationRequest from(Report report, String nickname) {
        return new NotificationRequest(
                NotificationType.REPORT,
                report.getType().toString(),
                report.getContent(),
                report.getTargetType(),
                report.getTargetId(),
                nickname
        );
    }
}
