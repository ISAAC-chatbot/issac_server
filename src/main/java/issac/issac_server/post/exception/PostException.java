package issac.issac_server.post.exception;

import issac.issac_server.common.exception.IssacException;

public class PostException extends IssacException {

    public PostException(PostErrorCode code) {
        super(code);
    }
}
