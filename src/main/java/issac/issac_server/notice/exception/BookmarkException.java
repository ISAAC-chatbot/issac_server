package issac.issac_server.notice.exception;

import issac.issac_server.common.exception.IssacException;

public class BookmarkException extends IssacException {

    public BookmarkException(BookmarkErrorCode code) {
        super(code);
    }
}
