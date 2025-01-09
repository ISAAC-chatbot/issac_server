package issac.issac_server.auth.application.oauth.kakao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
@FeignClient(
        name = "kakao-auth",
        url = "https://kapi.kakao.com"
)
public interface KakaoApiClient {

    @GetMapping("v2/user/me")
    KakaoInfoResponse findUserInfo(@RequestHeader("Authorization") String authorization);

    @GetMapping("v2/user/unlink")
    KakaoInfoResponse revoke(@RequestHeader("Authorization") String authorization, @RequestBody KaKaoRevokeRequest request);
}