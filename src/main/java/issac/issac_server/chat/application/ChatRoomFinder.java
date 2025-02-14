package issac.issac_server.chat.application;

import issac.issac_server.chat.domain.ChatRoom;
import issac.issac_server.chat.domain.repository.ChatRoomRepository;
import issac.issac_server.chat.exception.ChatErrorCode;
import issac.issac_server.chat.exception.ChatException;
import issac.issac_server.common.domain.EntityStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatRoomFinder {

    private final ChatRoomRepository chatRoomRepository;

    public Page<ChatRoom> findAll(Long userId, Pageable pageable) {
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id")
        );

        return chatRoomRepository.findAllByUserIdAndEntityStatus(userId, EntityStatus.ACTIVE, sortedPageable);
    }

    public ChatRoom find(Long chatRoomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new ChatException(ChatErrorCode.ROOM_NOT_FOUND));

        if (chatRoom.getEntityStatus() == EntityStatus.DELETED) {
            throw new ChatException(ChatErrorCode.ROOM_DELETED);
        }
        return chatRoom;
    }
}
