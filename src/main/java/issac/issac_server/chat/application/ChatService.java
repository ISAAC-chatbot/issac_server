package issac.issac_server.chat.application;

import issac.issac_server.chat.application.dto.ChatMessageCreateRequest;
import issac.issac_server.chat.application.dto.ChatMessageResponse;
import issac.issac_server.chat.application.dto.ChatRoomResponse;
import issac.issac_server.chat.application.message.ChatMessageAppender;
import issac.issac_server.chat.application.message.ChatMessageFinder;
import issac.issac_server.chat.domain.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRoomFinder chatRoomFinder;
    private final ChatRoomRemover chatRoomRemover;
    private final ChatRoomAppender chatRoomAppender;
    private final ChatMessageFinder messageFinder;
    private final ChatMessageAppender messageAppender;

    public Page<ChatRoomResponse> findChatRooms(Long userId, Pageable pageable) {
        return chatRoomFinder.findAll(userId, pageable).map(ChatRoomResponse::from);
    }

    @Transactional
    public void deleteChatRoom(Long userId, Long chatRoomId) {
        ChatRoom chatRoom = chatRoomFinder.find(chatRoomId);
        chatRoomRemover.remove(userId, chatRoom);
    }

    public Page<ChatMessageResponse> findMessages(Long userId, Long chatRoomId, Pageable pageable) {
        ChatRoom chatRoom = chatRoomFinder.find(chatRoomId);
        return messageFinder.findHistories(userId, chatRoom, pageable).map(ChatMessageResponse::from);
    }

    @Transactional
    public void saveMessage(Long userId, ChatMessageCreateRequest request) {
        ChatRoom chatRoom = request.getChatRoomId() == null ?
                chatRoomAppender.append(userId, request.getQuestion()) :
                chatRoomFinder.find(request.getChatRoomId());

        messageAppender.append(userId, chatRoom, request);
    }

}
