package issac.issac_server.auth.application.oauth.apple;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "apple-auth", url = "https://appleid.apple.com")
public interface AppleApiClient {

    @PostMapping(value = "/auth/token", consumes = "application/x-www-form-urlencoded")
    AppleTokenResponse findUserToken(@RequestBody AppleTokenRequest request);

    @PostMapping(value = "/auth/revoke", consumes = "application/x-www-form-urlencoded")
    void revoke(@RequestBody AppleRevokeRequest request);
}