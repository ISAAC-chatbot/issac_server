package issac.issac_server.auth.infrastructure;

import issac.issac_server.auth.exception.AuthErrorCode;
import issac.issac_server.auth.exception.AuthException;
import issac.issac_server.common.exception.IssacException;
import issac.issac_server.user.application.UserFinder;
import issac.issac_server.user.domain.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static issac.issac_server.auth.config.SecurityConfig.PERMITTED_URI;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserFinder userFinder;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        if (requestURI.equals("/api/v1/notices") && request.getMethod().equals("POST")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (isPermittedURI(requestURI)) {
            SecurityContextHolder.getContext().setAuthentication(null);
            filterChain.doFilter(request, response);
            return;
        }

        try{
            String accessToken = jwtTokenProvider.resolveToken(request);

            if (!jwtTokenProvider.validateAccessToken(accessToken)) {
                throw new AuthException(AuthErrorCode.INVALID_JWT);
            }

            String role = jwtTokenProvider.getRoleFromAccessToken(accessToken);

            if (role.equals("GUEST")) {
                // GUEST 역할을 가진 사용자를 SecurityContext에 설정
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                "anonymousUser",  // 사용자 이름
                                null,             // 자격 증명 (비밀번호 없음)
                                List.of(new SimpleGrantedAuthority("ROLE_GUEST")) // GUEST 권한 부여
                        )
                );
                filterChain.doFilter(request, response);
                return;
            }

            setAuthenticationToContext(accessToken);
            filterChain.doFilter(request, response);
        }
        catch (IssacException e){
            SecurityContextHolder.clearContext();

            // 기존 요청 정보를 request 속성에 저장
            request.setAttribute("exception", e);
//            request.setAttribute("originalMethod", request.getMethod());
//            request.setAttribute("originalURI", request.getRequestURI());
//            request.setAttribute("originalQueryString", request.getQueryString());

            // `/error/unauthorized`로 포워딩
            request.getRequestDispatcher("/error/unauthorized").forward(request, response);
        }


    }

    private boolean isPermittedURI(String requestURI) {
        return Arrays.stream(PERMITTED_URI)
                .anyMatch(permitted -> {
                    String replace = permitted.replace("*", "");
                    return requestURI.contains(replace) || replace.contains(requestURI);
                });
    }

    private User findUserByRefreshToken(String refreshToken) {
        String identifier = jwtTokenProvider.getIdentifierFromRefresh(refreshToken);
        return userFinder.find(Long.parseLong(identifier));
    }

    private void setAuthenticationToContext(String accessToken) {
        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
