package issac.issac_server.chat.application;

import issac.issac_server.chat.application.dto.ChatHistoryCreateRequest;
import issac.issac_server.chat.application.dto.ChatHistoryResponse;
import issac.issac_server.chat.application.dto.ChatRoomResponse;
import issac.issac_server.chat.application.history.ChatHistoryAppender;
import issac.issac_server.chat.application.history.ChatHistoryFinder;
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
    private final ChatHistoryFinder historyFinder;
    private final ChatHistoryAppender historyAppender;

    public Page<ChatRoomResponse> findChatRooms(Long userId, Pageable pageable) {
        return chatRoomFinder.findAll(userId, pageable).map(ChatRoomResponse::from);
    }

    @Transactional
    public void deleteChatRoom(Long userId, Long chatRoomId) {
        ChatRoom chatRoom = chatRoomFinder.find(chatRoomId);
        chatRoomRemover.remove(userId, chatRoom);
    }

    public Page<ChatHistoryResponse> findHistories(Long userId, Long chatRoomId, Pageable pageable) {
        ChatRoom chatRoom = chatRoomFinder.find(chatRoomId);
        return historyFinder.findHistories(userId, chatRoom, pageable).map(ChatHistoryResponse::from);
    }

    @Transactional
    public void saveHistory(Long userId, ChatHistoryCreateRequest request) {
        ChatRoom chatRoom = request.getChatRoomId() == null ?
                chatRoomAppender.append(userId, request.getQuestion()) :
                chatRoomFinder.find(request.getChatRoomId());

        historyAppender.append(userId, chatRoom, request);
    }

}
