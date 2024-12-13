package issac.issac_server.common.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    HttpStatus getStatusCode();

    String getErrorCode();

    String getErrorMessage();
}
