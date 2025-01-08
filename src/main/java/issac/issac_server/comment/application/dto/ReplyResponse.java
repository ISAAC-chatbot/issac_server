package issac.issac_server.comment.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import issac.issac_server.comment.domain.Comment;
import issac.issac_server.post.application.dto.response.UserInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static issac.issac_server.common.config.Constant.FORMAT_LOCAL_DATE_TIME;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyResponse {
    private Long commentId;
    private String content;
    private UserInfoResponse author;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FORMAT_LOCAL_DATE_TIME)
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FORMAT_LOCAL_DATE_TIME)
    private LocalDateTime modifiedAt;
    private Long likeCount;
    private Long dislikeCount;

    public static ReplyResponse from(Comment comment) {
        return ReplyResponse.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .author(UserInfoResponse.from(comment.getAuthor()))
                .createdAt(comment.getCreatedDateTime())
                .modifiedAt(comment.getLastModifiedDateTime())
                .likeCount(comment.getLikeCount())
                .dislikeCount(comment.getDislikeCount())
                .build();
    }
}
