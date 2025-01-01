package issac.issac_server.auth.exception;

import issac.issac_server.common.exception.IssacException;

public class AuthException extends IssacException {

    public AuthException(AuthErrorCode code) {
        super(code);
    }
}
