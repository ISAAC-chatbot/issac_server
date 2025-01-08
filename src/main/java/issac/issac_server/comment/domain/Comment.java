package issac.issac_server.comment.domain;

import issac.issac_server.comment.application.dto.CommentCreateRequest;
import issac.issac_server.comment.exception.CommentErrorCode;
import issac.issac_server.comment.exception.CommentException;
import issac.issac_server.common.domain.BaseTimeEntity;
import issac.issac_server.common.domain.EntityStatus;
import issac.issac_server.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@EqualsAndHashCode(of = "id", callSuper = false)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "comment")
@AllArgsConstructor
@Builder
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private Long postId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long likeCount;

    @Column(nullable = false)
    private Long dislikeCount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EntityStatus entityStatus;

    // **부모 댓글 매핑**
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_comment_id")
    private Comment parent; // 부모 댓글

    // **대댓글 매핑**
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> replies = new ArrayList<>(); // 대댓글 목록

    // 대댓글 추가 메서드
    public void addReply(Comment reply) {
        replies.add(reply);
        reply.setParent(this);
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public static Comment of(User author, Long postId, CommentCreateRequest request) {
        return Comment.builder()
                .postId(postId)
                .author(author)
                .content(request.getContent())
                .likeCount(0L)
                .dislikeCount(0L)
                .entityStatus(EntityStatus.ACTIVE)
                .build();
    }

    public void validateIsAuthor(User user) {
        if (this.author != user) {
            throw new CommentException(CommentErrorCode.USER_IS_NOT_AUTHOR);
        }
    }

    public void update(String content) {
        this.content = content;
    }

    public void active() {
        entityStatus = EntityStatus.ACTIVE;
    }

    public void delete() {
        entityStatus = EntityStatus.DELETED;
    }
}
