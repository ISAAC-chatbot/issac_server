package issac.issac_server.auth.application.oauth.google;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
@FeignClient(
        name = "google-auth",
        url = "${oauth.google.auth.url}")
public interface GoogleApiClient {

    @GetMapping("/oauth2/v1/userinfo")
    GoogleInfoResponse findUserInfo(@RequestHeader("Authorization") String authorization);

}