package issac.issac_server.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionResponse {

    private final String errorCode;
    private final String errorMessage;
    private final String exceptionType;
    private final String detailedMessage;

    public static ExceptionResponse from(ErrorCode errorCode, Throwable throwable) {
        return new ExceptionResponse(
                errorCode.getErrorCode(),
                errorCode.getErrorMessage(),
                throwable.getClass().getSimpleName(),
                throwable.getMessage()
        );
    }

    public static ExceptionResponse from(IssacException exception) {
        return new ExceptionResponse(
                exception.getErrorCode(),
                exception.getErrorMessage(),
                exception.getClass().getSimpleName(),
                exception.getMessage()
        );
    }
}
