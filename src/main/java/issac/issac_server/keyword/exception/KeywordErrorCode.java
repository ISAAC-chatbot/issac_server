package issac.issac_server.keyword.exception;

import issac.issac_server.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum KeywordErrorCode implements ErrorCode {

    NOT_EXIST(HttpStatus.NOT_FOUND, "KEYWORD_001", "키워드를 찾을 수 없습니다."),
    ALREADY_EXIST(HttpStatus.CONFLICT, "KEYWORD_002", "이미 존재하는 키워드입니다."),
    ;
    private final HttpStatus statusCode;
    private final String errorCode;
    private final String errorMessage;
}
