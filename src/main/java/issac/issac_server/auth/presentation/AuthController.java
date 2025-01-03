package issac.issac_server.auth.presentation;

import issac.issac_server.auth.application.AuthFacadeService;
import issac.issac_server.auth.application.dto.LoginRequest;
import issac.issac_server.auth.application.dto.LoginResponse;
import issac.issac_server.auth.application.dto.RefreshTokenRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthFacadeService authFacadeService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(authFacadeService.login(request));
    }

    @PostMapping("/guest-login")
    public ResponseEntity<LoginResponse> guestLogin() {
        return ResponseEntity.status(HttpStatus.OK).body(authFacadeService.guestLogin());
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(@RequestBody @Valid RefreshTokenRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authFacadeService.refresh(request));
    }
}
