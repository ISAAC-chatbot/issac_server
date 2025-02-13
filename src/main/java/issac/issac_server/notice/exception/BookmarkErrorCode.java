package issac.issac_server.notice.exception;

import issac.issac_server.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BookmarkErrorCode implements ErrorCode {

    NOT_EXIST(HttpStatus.NOT_FOUND, "BOOKMARK_001", "북마크를 찾을 수 없습니다."),
    USER_IS_NOT_AUTHOR(HttpStatus.FORBIDDEN, "BOOKMARK_002", "해당 북마크를 수정 할 권한이 없습니다"),
    ;
    private final HttpStatus statusCode;
    private final String errorCode;
    private final String errorMessage;
}
