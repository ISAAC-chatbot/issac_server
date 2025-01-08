package issac.issac_server.comment.application;

import issac.issac_server.comment.domain.Comment;
import issac.issac_server.comment.domain.CommentRepository;
import issac.issac_server.comment.exception.CommentErrorCode;
import issac.issac_server.comment.exception.CommentException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentFinder {

    private final CommentRepository commentRepository;

    public Comment find(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new CommentException(CommentErrorCode.NOT_FOUND));
    }

    public Slice<Comment> findAll(Long postId, Pageable pageable) {
        return commentRepository.findAllByPostIdAndParentIsNull(postId, pageable);
    }

    public Long count(Long postId) {
        return commentRepository.countAllByPostId(postId);
    }
}
