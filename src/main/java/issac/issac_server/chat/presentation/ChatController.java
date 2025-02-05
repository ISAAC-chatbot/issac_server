package issac.issac_server.chat.presentation;

import issac.issac_server.auth.config.auth.Auth;
import issac.issac_server.chat.application.ChatService;
import issac.issac_server.chat.application.dto.ChatHistoryCreateRequest;
import issac.issac_server.chat.application.dto.ChatHistoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat")
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/histories")
    public ResponseEntity<Page<ChatHistoryResponse>> findHistories(@Auth Long userId, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(chatService.findHistories(userId, pageable));
    }

    @PostMapping("/histories")
    public ResponseEntity<Void> saveHistory(@Auth Long userId, @RequestBody @Valid ChatHistoryCreateRequest request) {
        chatService.saveHistory(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
