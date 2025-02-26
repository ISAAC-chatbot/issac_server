package issac.issac_server.auth.presentation;

import issac.issac_server.auth.application.AuthFacadeService;
import issac.issac_server.auth.application.dto.*;
import issac.issac_server.auth.config.auth.Auth;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthFacadeService authFacadeService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid OAuthTokenRequest request) {
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

    @PostMapping("/email")
    public ResponseEntity<EmailResponse> sendEmailVerification(@RequestBody @Valid EmailRequest request) throws MessagingException {
        return ResponseEntity.status(HttpStatus.CREATED).body(authFacadeService.sendEmailVerification(request));
    }

    @DeleteMapping("/revoke")
    public ResponseEntity<Void> revoke(@Auth Long userId, @RequestBody @Valid UserRevokeRequest request) {
        authFacadeService.revoke(userId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
