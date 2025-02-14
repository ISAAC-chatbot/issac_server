package issac.issac_server.chat.application.message;

import issac.issac_server.chat.domain.ChatMessage;
import issac.issac_server.chat.domain.ChatRoom;
import issac.issac_server.chat.domain.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatMessageFinder {

    private final ChatMessageRepository chatMessageRepository;

    public Page<ChatMessage> findHistories(Long userId, ChatRoom chatRoom, Pageable pageable) {
        chatRoom.validateIsAuthor(userId);
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id")
        );

        return chatMessageRepository.findAllByChatRoomId(chatRoom.getId(), sortedPageable);
    }
}
