package issac.issac_server.notice.exception;

import issac.issac_server.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum NoticeErrorCode implements ErrorCode {

    SEARCH_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "NOTICE_001", "OpenSearch 검색 중 오류가 발생했습니다."),
    INVALID_SEARCH_CONDITION(HttpStatus.BAD_REQUEST, "NOTICE_002", "잘못된 검색 조건입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "NOTICE_003", "공지사항을 찾지 못했습니다.");;
    private final HttpStatus statusCode;
    private final String errorCode;
    private final String errorMessage;
}
