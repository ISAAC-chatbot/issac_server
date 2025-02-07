package issac.issac_server.chat.exception;

import issac.issac_server.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ChatErrorCode implements ErrorCode {

    HISTORY_NOT_FOUND(HttpStatus.NOT_FOUND, "CHAT_001", "채팅 히스토리를 찾을 수 없습니다."),
    USER_IS_NOT_AUTHOR(HttpStatus.FORBIDDEN, "CHAT_002", "해당 기록의 수정권한이 없습니다."),
    ;
    private final HttpStatus statusCode;
    private final String errorCode;
    private final String errorMessage;
}
