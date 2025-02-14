package issac.issac_server.chat.application.message;

import issac.issac_server.chat.application.dto.ChatMessageCreateRequest;
import issac.issac_server.chat.domain.ChatMessage;
import issac.issac_server.chat.domain.ChatRoom;
import issac.issac_server.chat.domain.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatMessageAppender {

    private final ChatMessageRepository chatMessageRepository;

    public void append(Long userId, ChatRoom chatRoom, ChatMessageCreateRequest request) {
        chatRoom.validateIsAuthor(userId);
        chatMessageRepository.save(ChatMessage.from(chatRoom.getId(), request));
    }
}
