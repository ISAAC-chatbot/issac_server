package issac.issac_server.reaction.presentation;

import issac.issac_server.auth.config.Auth;
import issac.issac_server.reaction.application.ReactionService;
import issac.issac_server.reaction.application.dto.ReactionCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/reactions")
@RequiredArgsConstructor
@RestController
public class ReactionController {

    private final ReactionService reactionService;

    @PostMapping
    public ResponseEntity<Void> save(
            @Auth Long userId,
            @Valid @RequestBody ReactionCreateRequest request
    ) {
        reactionService.save(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

