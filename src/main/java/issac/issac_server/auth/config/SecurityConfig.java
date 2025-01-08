package issac.issac_server.auth.config;

import issac.issac_server.auth.infrastructure.JwtAuthenticationFilter;
import issac.issac_server.auth.infrastructure.JwtTokenProvider;
import issac.issac_server.user.application.UserFinder;
import issac.issac_server.user.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    public static final String PERMITTED_URI[] = {"/api/v1/auth/**", "/oauth2/**", "/docs/**", "/favicon.ico",
            "/v3/api-docs/**", "/js/custom-swagger.js", "/health/**", "/error"};

    private static final String[] ALL_ROLES = Arrays.stream(Role.values())
            .map(Enum::name)
            .toArray(String[]::new);
    private final JwtTokenProvider jwtTokenProvider;
    private final UserFinder userFinder;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);

        http.cors(cors -> cors.configurationSource(request -> {
            var config = new org.springframework.web.cors.CorsConfiguration();
            config.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // 허용할 Origin 설정
            config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // 허용할 HTTP 메서드
            config.setAllowedHeaders(Arrays.asList("*")); // 허용할 헤더
            config.setAllowCredentials(true); // 인증 정보 포함 허용
            return config;
        }));

        //From 로그인 방식 disable
        http.formLogin(AbstractHttpConfigurer::disable);

        //http basic 인증 방식 disable
        http.httpBasic(AbstractHttpConfigurer::disable);

        //경로별 인가 작업
        http.authorizeHttpRequests(request -> request
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .requestMatchers(PERMITTED_URI).permitAll()
                .anyRequest().hasAnyRole(ALL_ROLES));

        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, userFinder),
                UsernamePasswordAuthenticationFilter.class);

        //세션 설정
        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return http.build();
    }
}
