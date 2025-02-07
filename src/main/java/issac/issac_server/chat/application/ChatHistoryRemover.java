package issac.issac_server.chat.application;

import issac.issac_server.chat.domain.ChatHistory;
import issac.issac_server.chat.domain.repository.ChatHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatHistoryRemover {

    private final ChatHistoryRepository chatHistoryRepository;

    public void remove(Long userId, ChatHistory chatHistory) {
        chatHistory.validateIsAuthor(userId);
        chatHistoryRepository.deleteById(chatHistory.getId());
    }
}
