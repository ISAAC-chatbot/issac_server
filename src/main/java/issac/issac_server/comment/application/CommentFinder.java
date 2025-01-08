package issac.issac_server.comment.application;

import issac.issac_server.comment.domain.Comment;
import issac.issac_server.comment.domain.CommentRepository;
import issac.issac_server.comment.exception.CommentErrorCode;
import issac.issac_server.comment.exception.CommentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentFinder {

    private final CommentRepository commentRepository;

    public Comment find(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new CommentException(CommentErrorCode.NOT_FOUND));
    }
}
