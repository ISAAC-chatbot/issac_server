package issac.issac_server.user.presentation;

import issac.issac_server.auth.config.auth.Auth;
import issac.issac_server.user.application.dto.ProfileResponse;
import issac.issac_server.user.application.dto.ProfileUpdateRequest;
import issac.issac_server.user.application.dto.ProfileCreateRequest;
import issac.issac_server.user.application.dto.UserResponse;
import issac.issac_server.user.application.profile.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    @Secured({"ROLE_USER", "ROLE_STUDENT", "ROLE_TEACHING_ASSISTANT", "ROLE_PROFESSOR", "ROLE_ADMIN"})
    public ResponseEntity<ProfileResponse> findMyProfile(@Auth Long userId) {
        return ResponseEntity.ok(profileService.findProfile(userId));
    }

    @PostMapping
    @Secured({"ROLE_UNREGISTERED_PROFILE"})
    public ResponseEntity<UserResponse> saveProfile(
            @Auth Long userId,
            @RequestBody ProfileCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(profileService.saveProfile(userId, request));
    }

    @PatchMapping
    @Secured({"ROLE_USER", "ROLE_STUDENT", "ROLE_TEACHING_ASSISTANT", "ROLE_PROFESSOR", "ROLE_ADMIN"})
    public ResponseEntity<ProfileResponse> updateMyProfile(@Auth Long userId, @RequestBody ProfileUpdateRequest request) {
        return ResponseEntity.ok(profileService.updateMyProfile(userId, request));
    }
}
