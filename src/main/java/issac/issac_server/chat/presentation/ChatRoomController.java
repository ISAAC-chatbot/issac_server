package issac.issac_server.chat.presentation;

import issac.issac_server.auth.config.auth.Auth;
import issac.issac_server.chat.application.ChatService;
import issac.issac_server.chat.application.dto.ChatRoomInfoResponse;
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
public class ChatRoomController {

    private final ChatService chatService;

    @GetMapping
    public ResponseEntity<Page<ChatRoomInfoResponse>> findChatRooms(@Auth Long userId, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(chatService.findChatRooms(userId, pageable));
    }

    @DeleteMapping("/{chatRoomId}")
    public ResponseEntity<Void> deleteChatRoom(@Auth Long userId, @PathVariable Long chatRoomId) {
        chatService.deleteChatRoom(userId, chatRoomId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
