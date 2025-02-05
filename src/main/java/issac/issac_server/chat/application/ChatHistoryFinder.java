package issac.issac_server.chat.application;

import issac.issac_server.chat.domain.ChatHistory;
import issac.issac_server.chat.domain.repository.ChatHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatHistoryFinder {

    private final ChatHistoryRepository chatHistoryRepository;

    public Page<ChatHistory> findHistories(Long userId, Pageable pageable) {
        return chatHistoryRepository.findAllByUserId(userId, pageable);
    }
}
