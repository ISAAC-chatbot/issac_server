package issac.issac_server.post.exception;

import issac.issac_server.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PostErrorCode implements ErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "POST_001", "게시글을 찾지 못했습니다.");;
    private final HttpStatus statusCode;
    private final String errorCode;
    private final String errorMessage;
}
