package issac.issac_server.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class IssacException extends RuntimeException {

    private final HttpStatus statusCode;
    private final String errorCode;
    private final String errorMessage;

    public IssacException(HttpStatus statusCode, String errorCode, String errorMessage) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
