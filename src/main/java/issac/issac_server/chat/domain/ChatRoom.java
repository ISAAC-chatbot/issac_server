package issac.issac_server.chat.domain;

import issac.issac_server.chat.exception.ChatErrorCode;
import issac.issac_server.chat.exception.ChatException;
import issac.issac_server.common.domain.BaseTimeEntity;
import issac.issac_server.common.domain.EntityStatus;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "chat_room")
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "chat_room_id")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String title;

    @Enumerated(EnumType.STRING)
    private EntityStatus entityStatus;

    public void active() {
        this.entityStatus = EntityStatus.ACTIVE;
    }

    public void delete() {
        this.entityStatus = EntityStatus.DELETED;
    }


    public static ChatRoom from(Long userId, String title) {
        return ChatRoom.builder()
                .userId(userId)
                .title(title)
                .build();
    }

    public void validateIsAuthor(Long userId) {
        if (!this.userId.equals(userId)) {
            throw new ChatException(ChatErrorCode.USER_IS_NOT_AUTHOR);
        }
    }
}
