package issac.issac_server.notification.application.dto;

import issac.issac_server.comment.domain.Comment;
import issac.issac_server.notice.domain.Bookmark;
import issac.issac_server.notice.domain.NoticeSource;
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

    private String fcmTitle;

    public static NotificationRequest of(NoticeSource source, String keyword, String title, TargetType entityType, String entityId, String author) {

        String notificationKeyword = entityType.equals(TargetType.NOTICE) ? keyword + '@' + source : keyword;

        return new NotificationRequest(
                NotificationType.KEYWORD,
                notificationKeyword,
                title,
                entityType,
                entityId,
                author,
                String.format("'%s' 알림이에요", keyword)
        );
    }

    public static NotificationRequest of(Bookmark bookmark, String title, TargetType entityType, String entityId, String author) {
        return new NotificationRequest(
                NotificationType.BOOKMARK,
                bookmark.getSource().toString(),
                title,
                entityType,
                entityId,
                author,
                "새로운 공지가 올라왔어요"
        );
    }

    public static NotificationRequest of(Post post, Comment comment) {
        return new NotificationRequest(
                NotificationType.COMMENT,
                post.getTitle(),
                comment.getContent(),
                TargetType.COMMENT,
                comment.getId().toString(),
                comment.getAuthor().getProfile().getNickname(),
                "새로운 댓글이 달렸어요"
        );
    }

    public static NotificationRequest from(Report report, String nickname) {
        return new NotificationRequest(
                NotificationType.REPORT,
                report.getType().toString(),
                report.getContent(),
                report.getTargetType(),
                report.getTargetId(),
                nickname,
                "새로운 신고가 접수됐어요"
        );
    }
}
