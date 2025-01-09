package issac.issac_server.auth.application.oauth.google;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Component
@FeignClient(name = "google-auth", configuration = GoogleFeignConfig.class)
public interface GoogleApiClient {

    @GetMapping("/userinfo")
    GoogleInfoResponse findUserInfo(@RequestHeader("Authorization") String authorization);

    @PostMapping(value = "/revoke", consumes = "application/x-www-form-urlencoded")
    void revoke(@RequestParam String token, @RequestBody Map<String, Object> emptyBody);
}