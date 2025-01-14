package issac.issac_server.notification.exception;

import issac.issac_server.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum NotificationErrorCode implements ErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "NOTIFICATION_001", "알림을 찾지 못했습니다."),
    USER_IS_NOT_AUTHOR(HttpStatus.FORBIDDEN, "NOTIFICATION_002", "해당 알림을 수정 할 권한이 없습니다");
    private final HttpStatus statusCode;
    private final String errorCode;
    private final String errorMessage;
}
