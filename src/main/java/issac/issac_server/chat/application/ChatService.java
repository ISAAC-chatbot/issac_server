package issac.issac_server.chat.application;

import issac.issac_server.chat.application.dto.ChatHistoryCreateRequest;
import issac.issac_server.chat.application.dto.ChatHistoryResponse;
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

    public Page<ChatHistoryResponse> findHistories(Long userId, Pageable pageable) {
        return historyFinder.findHistories(userId, pageable).map(ChatHistoryResponse::from);
    }

    @Transactional
    public void saveHistory(Long userId, ChatHistoryCreateRequest request) {
        historyAppender.append(userId, request);
    }
}
