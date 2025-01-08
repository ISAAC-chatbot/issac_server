package issac.issac_server.post.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import issac.issac_server.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

import static issac.issac_server.common.config.Constant.FORMAT_LOCAL_DATE_TIME;

@Getter
@AllArgsConstructor
public class PostPreviewResponse {

    private Long postId;
    private String title;
    private String previewContent;
    private String thumbnailPhotoUrl;
    private UserInfoResponse author;
    private Long likeCount;
    private Long commentCount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FORMAT_LOCAL_DATE_TIME)
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FORMAT_LOCAL_DATE_TIME)
    private LocalDateTime modifiedAt;

    public static PostPreviewResponse from(Post post){
        return new PostPreviewResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getThumbnailPhotoUrl(),
                UserInfoResponse.from(post.getAuthor()),
                post.getLikeCount(),
                post.getCommentCount(),
                post.getCreatedDateTime(),
                post.getLastModifiedDateTime()
        );
    }
}
