package issac.issac_server.user.presentation;

import issac.issac_server.auth.config.Auth;
import issac.issac_server.user.application.UserService;
import issac.issac_server.user.application.dto.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    @Secured({"ROLE_UNREGISTERED_PROFILE"})
    public ResponseEntity<Void> signup(
            @Auth Long userId,
            @RequestBody UserCreateRequest request) {
        userService.signup(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
