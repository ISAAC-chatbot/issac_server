package issac.issac_server.comment.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import issac.issac_server.comment.domain.Comment;
import issac.issac_server.common.domain.EntityStatus;
import issac.issac_server.post.application.dto.response.UserInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static issac.issac_server.common.config.Constant.FORMAT_LOCAL_DATE_TIME;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {

    private Long commentId;
    private String content;
    private UserInfoResponse author;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FORMAT_LOCAL_DATE_TIME)
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FORMAT_LOCAL_DATE_TIME)
    private LocalDateTime modifiedAt;
    private boolean deleted;
    private Long likeCount;
    private Long dislikeCount;
    private List<ReplyResponse> replies;

    public static CommentResponse from(Comment comment) {

        boolean isDeleted = comment.getEntityStatus() == EntityStatus.DELETED;

        if (isDeleted) {
            return CommentResponse.builder()
                    .commentId(comment.getId())
                    .content("삭제된 댓글입니다")
                    .deleted(true)
                    .author(UserInfoResponse.forDeletedUser())
                    .replies(getReplies(comment))
                    .build();
        }

        return CommentResponse.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .author(UserInfoResponse.from(comment.getAuthor()))
                .createdAt(comment.getCreatedDateTime())
                .modifiedAt(comment.getLastModifiedDateTime())
                .deleted(false)
                .likeCount(comment.getLikeCount())
                .dislikeCount(comment.getDislikeCount())
                .replies(getReplies(comment))
                .build();
    }

    private static List<ReplyResponse> getReplies(Comment comment) {
        return comment.getReplies() == null ? null
                : comment.getReplies().stream()
                .filter(reply -> reply.getEntityStatus() != EntityStatus.DELETED)
                .map(ReplyResponse::from)
                .toList();
    }
}
