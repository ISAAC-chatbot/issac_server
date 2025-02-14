package issac.issac_server.chat.application;

import issac.issac_server.chat.domain.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatRoomRemover {

    public void remove(Long userId, ChatRoom chatRoom) {
        chatRoom.validateIsAuthor(userId);
        chatRoom.delete();
    }
}
