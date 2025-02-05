package issac.issac_server.chat.domain.repository;

import issac.issac_server.chat.domain.ChatHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long> {

    Page<ChatHistory> findAllByUserId(Long userId, Pageable pageable);
}
