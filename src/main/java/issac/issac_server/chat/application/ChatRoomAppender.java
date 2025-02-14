package issac.issac_server.chat.application;

import issac.issac_server.chat.domain.ChatRoom;
import issac.issac_server.chat.domain.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatRoomAppender {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoom append(Long userId, String title){
        ChatRoom chatRoom = ChatRoom.from(userId, title);
        chatRoom.active();
        return chatRoomRepository.save(chatRoom);
    }
}
