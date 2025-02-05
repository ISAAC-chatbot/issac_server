package issac.issac_server.chat.application;

import issac.issac_server.chat.application.dto.ChatHistoryCreateRequest;
import issac.issac_server.chat.domain.ChatHistory;
import issac.issac_server.chat.domain.repository.ChatHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatHistoryAppender {

    private final ChatHistoryRepository chatHistoryRepository;

    public void append(Long userId, ChatHistoryCreateRequest request) {
        chatHistoryRepository.save(ChatHistory.from(userId, request));
    }
}
