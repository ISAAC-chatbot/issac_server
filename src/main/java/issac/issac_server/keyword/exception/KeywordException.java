package issac.issac_server.keyword.exception;

import issac.issac_server.common.exception.IssacException;

public class KeywordException extends IssacException {

    public KeywordException(KeywordErrorCode code) {
        super(code);
    }
}
