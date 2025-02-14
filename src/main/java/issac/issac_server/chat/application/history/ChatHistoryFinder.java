package issac.issac_server.chat.application.history;

import issac.issac_server.chat.domain.ChatHistory;
import issac.issac_server.chat.domain.ChatRoom;
import issac.issac_server.chat.domain.repository.ChatHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatHistoryFinder {

    private final ChatHistoryRepository chatHistoryRepository;

    public Page<ChatHistory> findHistories(Long userId, ChatRoom chatRoom, Pageable pageable) {
        chatRoom.validateIsAuthor(userId);
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id")
        );

        return chatHistoryRepository.findAllByChatRoomId(chatRoom.getId(), sortedPageable);
    }
}
