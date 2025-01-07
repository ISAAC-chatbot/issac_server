package issac.issac_server.reaction.presentation;

import issac.issac_server.auth.config.Auth;
import issac.issac_server.reaction.application.ReactionService;
import issac.issac_server.reaction.application.dto.ReactionCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/reactions")
@RequiredArgsConstructor
@RestController
@Secured({"ROLE_USER", "ROLE_STUDENT", "ROLE_TEACHING_ASSISTANT", "ROLE_PROFESSOR", "ROLE_ADMIN"})
public class ReactionController {

    private final ReactionService reactionService;

    @PutMapping
    public ResponseEntity<Void> toggle(
            @Auth Long userId,
            @Valid @RequestBody ReactionCreateRequest request
    ) {
        reactionService.save(userId, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

