package issac.issac_server.auth.infrastructure;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import issac.issac_server.auth.exception.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

import static issac.issac_server.auth.exception.AuthErrorCode.INVALID_EXPIRED_JWT;
import static issac.issac_server.auth.exception.AuthErrorCode.INVALID_JWT;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class JwtUtil {

    public TokenStatus getTokenStatus(String token, Key secretKey) {

        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return TokenStatus.AUTHENTICATED;
        } catch (ExpiredJwtException | IllegalArgumentException e) {
            throw new AuthException(INVALID_EXPIRED_JWT);
        } catch (JwtException e) {
            throw new AuthException(INVALID_JWT);
        }
    }

    public Key getSigningKey(String secretKey) {
        String encodedKey = encodeToBase64(secretKey);
        return Keys.hmacShaKeyFor(encodedKey.getBytes(StandardCharsets.UTF_8));
    }

    private String encodeToBase64(String secretKey) {
        return Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
}
