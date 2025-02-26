package issac.issac_server.user.presentation;

import issac.issac_server.auth.config.auth.Auth;
import issac.issac_server.user.application.UserService;
import issac.issac_server.user.application.dto.SettingResponse;
import issac.issac_server.user.application.dto.UserCreateRequest;
import issac.issac_server.user.application.dto.UserResponse;
import issac.issac_server.user.domain.SettingType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @Secured({"ROLE_USER", "ROLE_STUDENT", "ROLE_TEACHING_ASSISTANT", "ROLE_PROFESSOR", "ROLE_ADMIN"})
    public ResponseEntity<UserResponse> findUser(@Auth Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUser(userId));
    }


    @PostMapping("/signup")
    @Secured({"ROLE_UNREGISTERED_PROFILE"})
    public ResponseEntity<UserResponse> signup(
            @Auth Long userId,
            @RequestBody UserCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signup(userId, request));
    }

    @GetMapping("/settings/{settingItem}")
    @Secured({"ROLE_USER", "ROLE_STUDENT", "ROLE_TEACHING_ASSISTANT", "ROLE_PROFESSOR", "ROLE_ADMIN"})
    public ResponseEntity<SettingResponse> findSetting(
            @Auth Long userId,
            @PathVariable SettingType settingItem
    ) {
        return ResponseEntity.ok(userService.findSetting(userId, settingItem));
    }

    @PatchMapping("/settings/{settingItem}")
    @Secured({"ROLE_USER", "ROLE_STUDENT", "ROLE_TEACHING_ASSISTANT", "ROLE_PROFESSOR", "ROLE_ADMIN"})
    public ResponseEntity<SettingResponse> toggleSetting(
            @Auth Long userId,
            @PathVariable SettingType settingItem
    ) {
        return ResponseEntity.ok().body(userService.toggleSetting(userId, settingItem));
    }
}
