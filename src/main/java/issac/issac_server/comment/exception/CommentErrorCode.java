package issac.issac_server.comment.exception;

import issac.issac_server.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CommentErrorCode implements ErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "COMMENT_001", "댓글을 찾을 수 없습니다."),
    USER_IS_NOT_AUTHOR(HttpStatus.FORBIDDEN, "COMMENT_002", "해당 댓글의 수정권한이 없습니다."),
    ;
    private final HttpStatus statusCode;
    private final String errorCode;
    private final String errorMessage;
}
