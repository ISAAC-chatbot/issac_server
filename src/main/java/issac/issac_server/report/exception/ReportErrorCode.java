package issac.issac_server.report.exception;

import issac.issac_server.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReportErrorCode implements ErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "REPORT_001", "신고 글을 찾지 못했습니다."),
    ;
    private final HttpStatus statusCode;
    private final String errorCode;
    private final String errorMessage;
}
