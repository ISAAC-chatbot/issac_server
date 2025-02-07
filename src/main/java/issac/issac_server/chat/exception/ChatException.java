package issac.issac_server.chat.exception;

import issac.issac_server.common.exception.IssacException;

public class ChatException extends IssacException {

    public ChatException(ChatErrorCode code) {
        super(code);
    }
}
