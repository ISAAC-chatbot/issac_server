package issac.issac_server.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class IssacException extends RuntimeException {

    private final HttpStatus statusCode;
    private final String errorCode;
    private final String errorMessage;

    public IssacException(ErrorCode errorCode) {
        this.statusCode = errorCode.getStatusCode();
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }
}
