package issac.issac_server.auth.exception;

import issac.issac_server.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements ErrorCode {

    UNSUPPORTED_OAUTH_TYPE(HttpStatus.BAD_REQUEST, "AUTH_001", "지원되지 않는 소셜 로그인입니다."),
    USER_NOT_REGISTERED(HttpStatus.UNAUTHORIZED, "AUTH_002", "가입하지 않은 유저입니다."),
    INVALID_JWT(HttpStatus.UNAUTHORIZED, "AUTH_003", "유효하지 않은 토큰입니다."),
    JWT_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "AUTH_004", "토큰을 찾을 수 없습니다."),
    INVALID_EXPIRED_JWT(HttpStatus.UNAUTHORIZED, "AUTH_005", "만료된 토큰입니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "AUTH_006", "Refresh 토큰을 찾을 수 없습니다."),
    INVALID_EMAIL_DOMAIN(HttpStatus.BAD_REQUEST, "AUTH_007", "이메일 도메인이 유효하지 않습니다."),
    ;
    private final HttpStatus statusCode;
    private final String errorCode;
    private final String errorMessage;
}
