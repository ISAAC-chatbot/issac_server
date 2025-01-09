package issac.issac_server.auth.application.oauth.google;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleFeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            if (requestTemplate.path().contains("/userinfo")) {
                requestTemplate.target("https://www.googleapis.com/oauth2/v1");
            } else if (requestTemplate.path().contains("/revoke")) {
                requestTemplate.target("https://oauth2.googleapis.com");
            }
        };
    }
}
