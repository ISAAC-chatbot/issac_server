package issac.issac_server.chat.application;

import issac.issac_server.chat.application.dto.ChatHistoryCreateRequest;
import issac.issac_server.chat.application.dto.ChatHistoryResponse;
import issac.issac_server.chat.domain.ChatHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatHistoryFinder historyFinder;
    private final ChatHistoryAppender historyAppender;
    private final ChatHistoryRemover historyRemover;

    public Page<ChatHistoryResponse> findHistories(Long userId, Pageable pageable) {
        return historyFinder.findHistories(userId, pageable).map(ChatHistoryResponse::from);
    }

    @Transactional
    public void saveHistory(Long userId, ChatHistoryCreateRequest request) {
        historyAppender.append(userId, request);
    }

    @Transactional
    public void deleteHistory(Long userId, Long historyId) {
        ChatHistory chatHistory = historyFinder.find(historyId);
        historyRemover.remove(userId, chatHistory);
    }
}
