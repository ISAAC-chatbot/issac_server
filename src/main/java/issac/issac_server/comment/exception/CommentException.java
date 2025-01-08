package issac.issac_server.comment.exception;

import issac.issac_server.common.exception.IssacException;

public class CommentException extends IssacException {

    public CommentException(CommentErrorCode code) {
        super(code);
    }
}
