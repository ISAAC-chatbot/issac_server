package issac.issac_server.post.application.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import issac.issac_server.post.domain.Post;
import issac.issac_server.post.domain.PostPhoto;
import issac.issac_server.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static issac.issac_server.common.config.Constant.FORMAT_LOCAL_DATE_TIME;

@Getter
@AllArgsConstructor
@Builder
public class PostResponse {

    private Long postId;
    private String title;
    private String content;
    private List<ReactionStatusResponse> reactions;
    private UserInfoResponse author;
    private List<String> photoUrls;
    private Long commentCount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FORMAT_LOCAL_DATE_TIME)
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FORMAT_LOCAL_DATE_TIME)
    private LocalDateTime modifiedAt;

    public static PostResponse from(Post post, List<PostPhoto> postPhotos, User author) {
        return PostResponse.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .reactions(new ArrayList<>())
                .author(UserInfoResponse.from(author))
                .photoUrls(PostPhoto.extractPhotoUrls(postPhotos))
                .commentCount(post.getCommentCount())
                .createdAt(post.getCreatedDateTime())
                .modifiedAt(post.getLastModifiedDateTime())
                .build();
    }

    public static PostResponse from(Post post, List<PostPhoto> postPhotos, List<ReactionStatusResponse> reactions) {
        return PostResponse.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .reactions(reactions)
                .author(UserInfoResponse.from(post.getAuthor()))
                .photoUrls(PostPhoto.extractPhotoUrls(postPhotos))
                .commentCount(post.getCommentCount())
                .createdAt(post.getCreatedDateTime())
                .modifiedAt(post.getLastModifiedDateTime())
                .build();
    }
}
