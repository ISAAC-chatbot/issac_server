package issac.issac_server.auth.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthWebController {

    @GetMapping("/oauth/google/login/code")
    public ResponseEntity<String> googleRedirect(@RequestParam String code) {
        return ResponseEntity.ok().body(code);
    }

    @GetMapping("/oauth/kakao/login/code")
    public ResponseEntity<String> kakaoRedirect(@RequestParam String code) {
        return ResponseEntity.ok().body(code);
    }

    @PostMapping("/oauth/apple/login/code")
    public ResponseEntity<String> appleRedirect(@RequestParam String code) {
        return ResponseEntity.ok().body(code);
    }

}
