package issac.issac_server.post.domain;

import issac.issac_server.common.domain.BaseTimeEntity;
import issac.issac_server.common.domain.EntityStatus;
import issac.issac_server.post.application.PostUpdateRequest;
import issac.issac_server.post.application.dto.PostCreateRequest;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@EqualsAndHashCode(of = "id", callSuper = false)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "post")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    private String thumbnailPhotoUrl;

    @Column(nullable = false)
    private Long likeCount;

    @Column(nullable = false)
    private Long commentCount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EntityStatus entityStatus;

    @Builder
    public Post(Long userId, String title, String content, String thumbnailPhotoUrl) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.thumbnailPhotoUrl = thumbnailPhotoUrl;
        this.likeCount = 0L;
        this.commentCount = 0L;
    }

    public static Post of(Long userId, PostCreateRequest request) {
        return Post.builder()
                .userId(userId)
                .title(request.getTitle())
                .content(request.getContent())
                .thumbnailPhotoUrl(request.getThumbnailPhotoUrl())
                .build();
    }

    public void active() {
        entityStatus = EntityStatus.ACTIVE;
    }

    public void delete() {
        entityStatus = EntityStatus.DELETED;
    }

    public void update(PostUpdateRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
        this.thumbnailPhotoUrl = request.getThumbnailPhotoUrl();
    }
}
