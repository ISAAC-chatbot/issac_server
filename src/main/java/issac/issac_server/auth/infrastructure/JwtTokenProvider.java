package issac.issac_server.auth.infrastructure;

import io.jsonwebtoken.Jwts;
import issac.issac_server.auth.domain.RefreshToken;
import issac.issac_server.auth.domain.RefreshTokenRepository;
import issac.issac_server.auth.exception.AuthException;
import issac.issac_server.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;

import static issac.issac_server.auth.exception.AuthErrorCode.INVALID_JWT;
import static issac.issac_server.auth.exception.AuthErrorCode.JWT_TOKEN_NOT_FOUND;
import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.util.StringUtils.hasText;

@Component
@Transactional(readOnly = true)
@Slf4j
public class JwtTokenProvider {

    private final CustomUserDetails customUserDetails;
    private final JwtGenerator jwtGenerator;
    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    private final Key ACCESS_SECRET_KEY;
    private final Key REFRESH_SECRET_KEY;
    private final long ACCESS_EXPIRATION;
    private final long REFRESH_EXPIRATION;

    private static final String AUTHORIZATION_PREFIX = "Bearer ";
    private static final String EMPTY = "";


    public JwtTokenProvider(CustomUserDetails customUserDetails, JwtGenerator jwtGenerator,
                            JwtUtil jwtUtil, RefreshTokenRepository refreshTokenRepository,
                            @Value("${jwt.access-secret}") String ACCESS_SECRET_KEY,
                            @Value("${jwt.refresh-secret}") String REFRESH_SECRET_KEY,
                            @Value("${jwt.access-expiration}") long ACCESS_EXPIRATION,
                            @Value("${jwt.refresh-expiration}") long REFRESH_EXPIRATION) {
        this.customUserDetails = customUserDetails;
        this.jwtGenerator = jwtGenerator;
        this.jwtUtil = jwtUtil;
        this.refreshTokenRepository = refreshTokenRepository;
        this.ACCESS_SECRET_KEY = jwtUtil.getSigningKey(ACCESS_SECRET_KEY);
        this.REFRESH_SECRET_KEY = jwtUtil.getSigningKey(REFRESH_SECRET_KEY);
        this.ACCESS_EXPIRATION = ACCESS_EXPIRATION;
        this.REFRESH_EXPIRATION = REFRESH_EXPIRATION;
    }

    public String generateAccessToken(User user) {
        return jwtGenerator.generateToken(ACCESS_SECRET_KEY, ACCESS_EXPIRATION, user);
    }

    @Transactional
    public String generateRefreshToken(User user) {
        String refreshToken = jwtGenerator.generateToken(REFRESH_SECRET_KEY, REFRESH_EXPIRATION, user);
        refreshTokenRepository.save(new RefreshToken(user.getId(), refreshToken));
        return refreshToken;
    }

    public String generateGuestAccessToken() {
        return jwtGenerator.generateGuestToken(ACCESS_SECRET_KEY, ACCESS_EXPIRATION);
    }

    public boolean validateAccessToken(String token) {
        return jwtUtil.getTokenStatus(token, ACCESS_SECRET_KEY) == TokenStatus.AUTHENTICATED;
    }

    public boolean validateRefreshToken(String token) {
        return jwtUtil.getTokenStatus(token, REFRESH_SECRET_KEY) == TokenStatus.AUTHENTICATED;
    }

    public String resolveToken(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION);
        validate(header);
        return header.replace(AUTHORIZATION_PREFIX, EMPTY);
    }


    private static void validate(String header) {
        if (!hasText(header) || !header.startsWith(AUTHORIZATION_PREFIX)) {
            throw new AuthException(JWT_TOKEN_NOT_FOUND);
        }
    }

    public Authentication getAuthentication(String token) {
        UserDetails principal = customUserDetails.loadUserByUsername(getUserPk(token, ACCESS_SECRET_KEY));
        return new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());
    }

    private String getUserPk(String token, Key secretKey) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getIdentifierFromRefresh(String refreshToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(REFRESH_SECRET_KEY)
                    .build()
                    .parseClaimsJws(refreshToken)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            throw new AuthException(INVALID_JWT);
        }
    }

    public String getRoleFromAccessToken(String accessToken) {
        return Jwts.parserBuilder()
                .setSigningKey(ACCESS_SECRET_KEY)
                .build()
                .parseClaimsJws(accessToken)
                .getBody()
                .get("Role", String.class);
    }
}
