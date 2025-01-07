package issac.issac_server.user.presentation;

import issac.issac_server.auth.config.Auth;
import issac.issac_server.user.application.dto.ProfileResponse;
import issac.issac_server.user.application.profile.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users/profile")
@RequiredArgsConstructor
@Secured({"ROLE_USER", "ROLE_STUDENT", "ROLE_TEACHING_ASSISTANT", "ROLE_PROFESSOR", "ROLE_ADMIN"})
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<ProfileResponse> findMyProfile(@Auth Long userId) {
        return ResponseEntity.ok(profileService.findProfile(userId));
    }

}
