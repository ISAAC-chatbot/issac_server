package issac.issac_server.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.net.BindException;

@Log4j2
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(IssacException.class)
    public ResponseEntity<ExceptionResponse> handleIssacException(HttpServletRequest request, IssacException e) {
        log.error("[IssacException] Method: {}, RequestURI: {}, Exception: {}, Message: {}",
                request::getMethod, request::getRequestURI,
                e::getClass, e::getMessage);

        ExceptionResponse response = ExceptionResponse.from(e);
        return ResponseEntity.status(e.getStatusCode()).body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(HttpServletRequest request, RuntimeException e) {
        log.error("[RuntimeException] Method: {}, RequestURI: {}, Exception: {}, Message: {}",
                request::getMethod, request::getRequestURI,
                e::getClass, e::getMessage);

        log.error(e.getMessage(), e);
        return convert(GlobalErrorCode.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<ExceptionResponse> handleMissingServletRequestParameterException(HttpServletRequest request, Exception e) {
        log.error("[ValidationException] Method: {}, RequestURI: {}, Exception: {}, Message: {}",
                request::getMethod, request::getRequestURI,
                e::getClass, e::getMessage);

        return convert(GlobalErrorCode.MISSING_PARAMETER_ERROR, e);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(HttpServletRequest request, Exception e) {
        log.error("[ValidationException] Method: {}, RequestURI: {}, Exception: {}, Message: {}",
                request::getMethod, request::getRequestURI,
                e::getClass, e::getMessage);

        return convert(GlobalErrorCode.VALIDATION_ERROR, e);
    }

    @ExceptionHandler({NoHandlerFoundException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ExceptionResponse> handleUriExceptions(HttpServletRequest request, Exception e) {
        log.error("[UriException] Method: {}, RequestURI: {}, Exception: {}, Message: {}",
                request::getMethod, request::getRequestURI,
                e::getClass, e::getMessage);

        return convert(GlobalErrorCode.NOT_SUPPORTED_URI_ERROR, e);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionResponse> handleMethodNotSupportedException(HttpServletRequest request, HttpRequestMethodNotSupportedException e) {
        log.error("[HttpMethodNotSupportedException] Method: {}, RequestURI: {}, Exception: {}, Message: {}",
                request::getMethod, request::getRequestURI,
                e::getClass, e::getMessage);

        return convert(GlobalErrorCode.NOT_SUPPORTED_METHOD_ERROR, e);
    }

    private ResponseEntity<ExceptionResponse> convert(ErrorCode errorCode, Throwable throwable) {
        HttpStatus statusCode = errorCode.getStatusCode();
        ExceptionResponse response = ExceptionResponse.from(errorCode, throwable);

        return ResponseEntity.status(statusCode).body(response);
    }
}
