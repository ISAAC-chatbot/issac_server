package issac.issac_server.chat.domain.repository;

import issac.issac_server.chat.domain.ChatRoom;
import issac.issac_server.common.domain.EntityStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    Page<ChatRoom> findAllByUserIdAndEntityStatus(Long userId, EntityStatus entityStatus, Pageable pageable);
}
