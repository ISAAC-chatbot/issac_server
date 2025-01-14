package issac.issac_server.notification.exception;

import issac.issac_server.common.exception.IssacException;

public class NotificationException extends IssacException {

    public NotificationException(NotificationErrorCode code) {
        super(code);
    }
}
