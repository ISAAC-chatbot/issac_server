package issac.issac_server.notice.exception;

import issac.issac_server.common.exception.IssacException;

public class NoticeException extends IssacException {

    public NoticeException(NoticeErrorCode code) {
        super(code);
    }
}
