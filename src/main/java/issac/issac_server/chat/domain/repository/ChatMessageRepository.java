package issac.issac_server.chat.domain.repository;

import issac.issac_server.chat.domain.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    Page<ChatMessage> findAllByChatRoomId(Long chatRoomId, Pageable pageable);
}
