package issac.issac_server.post.domain;

import issac.issac_server.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "post_photo")
public class PostPhoto extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_photo_id")
    private Long id;

    private Long postId;

    @Column(nullable = false)
    private String photoUrl;

    @Builder
    public PostPhoto(Long id, Long postId, String photoUrl) {
        this.id = id;
        this.postId = postId;
        this.photoUrl = photoUrl;
    }

    public static List<PostPhoto> createPostPhotos(Long postId, List<String> photoUrls) {
        return photoUrls.stream()
                .map(url -> PostPhoto.builder()
                        .postId(postId)
                        .photoUrl(url)
                        .build())
                .collect(Collectors.toList());
    }

    public static List<String> extractPhotoUrls(List<PostPhoto> postPhotos) {
        return postPhotos != null
                ? postPhotos.stream().map(PostPhoto::getPhotoUrl).collect(Collectors.toList())
                : Collections.emptyList();
    }
}
