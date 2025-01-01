package issac.issac_server.user.exception;

import issac.issac_server.common.exception.IssacException;

public class UserException extends IssacException {

    public UserException(UserErrorCode code) {
        super(code);
    }
}
