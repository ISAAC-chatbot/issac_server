package issac.issac_server.post.domain;

import issac.issac_server.common.domain.BaseTimeEntity;
import issac.issac_server.common.domain.EntityStatus;
import issac.issac_server.post.application.dto.request.PostCreateRequest;
import issac.issac_server.post.application.dto.request.PostUpdateRequest;
import issac.issac_server.post.exception.PostErrorCode;
import issac.issac_server.post.exception.PostException;
import issac.issac_server.user.domain.University;
import issac.issac_server.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@EqualsAndHashCode(of = "id", callSuper = false)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "post")
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private University university;

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
    public Post(User author, String title, String content, String thumbnailPhotoUrl) {
        this.author = author;
        this.university = author.getProfile().getUniversity();
        this.title = title;
        this.content = content;
        this.thumbnailPhotoUrl = thumbnailPhotoUrl;
        this.likeCount = 0L;
        this.commentCount = 0L;
        this.entityStatus = EntityStatus.ACTIVE;
    }

    public static Post of(User user, PostCreateRequest request) {
        return Post.builder()
                .author(user)
                .title(request.getTitle())
                .content(request.getContent())
                .thumbnailPhotoUrl(request.getThumbnailPhotoUrl())
                .build();
    }

    public void active() {
        this.entityStatus = EntityStatus.ACTIVE;
    }

    public void delete() {
        this.entityStatus = EntityStatus.DELETED;
    }

    public void update(PostUpdateRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
        this.thumbnailPhotoUrl = request.getThumbnailPhotoUrl();
    }

    public void updateLikeCount(Long count) {
        this.likeCount = count;
    }

    public void updateCommentCount(Long count) {
        this.commentCount = count;
    }

    public void validatePostIsActive() {
        if (this.entityStatus != EntityStatus.ACTIVE) {
            throw new PostException(PostErrorCode.DELETED);
        }
    }

    public void validateAuthor(Long userId) {
        if (!this.author.getId().equals(userId)) {
            throw new PostException(PostErrorCode.USER_IS_NOT_AUTHOR);
        }
    }
}
