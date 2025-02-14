package issac.issac_server.chat.presentation;

import issac.issac_server.auth.config.auth.Auth;
import issac.issac_server.chat.application.ChatService;
import issac.issac_server.chat.application.dto.ChatMessageCreateRequest;
import issac.issac_server.chat.application.dto.ChatMessageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat")
@Secured({"ROLE_USER", "ROLE_STUDENT", "ROLE_TEACHING_ASSISTANT", "ROLE_PROFESSOR", "ROLE_ADMIN"})
public class ChatMessageController {

    private final ChatService chatService;

    @GetMapping("/{chatRoomId}/messages")
    public ResponseEntity<Page<ChatMessageResponse>> findMessages(@Auth Long userId, @PathVariable Long chatRoomId, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(chatService.findMessages(userId, chatRoomId, pageable));
    }

    @PostMapping("/messages")
    public ResponseEntity<Void> saveMessage(@Auth Long userId, @RequestBody @Valid ChatMessageCreateRequest request) {
        chatService.saveMessage(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
