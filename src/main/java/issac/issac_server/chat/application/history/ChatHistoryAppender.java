package issac.issac_server.chat.application.history;

import issac.issac_server.chat.application.dto.ChatHistoryCreateRequest;
import issac.issac_server.chat.domain.ChatHistory;
import issac.issac_server.chat.domain.ChatRoom;
import issac.issac_server.chat.domain.repository.ChatHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatHistoryAppender {

    private final ChatHistoryRepository chatHistoryRepository;

    public void append(Long userId, ChatRoom chatRoom, ChatHistoryCreateRequest request) {
        chatRoom.validateIsAuthor(userId);
        chatHistoryRepository.save(ChatHistory.from(chatRoom.getId(), request));
    }
}
