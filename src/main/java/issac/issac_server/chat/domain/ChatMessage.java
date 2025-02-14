package issac.issac_server.chat.domain;

import issac.issac_server.chat.application.dto.ChatMessageCreateRequest;
import issac.issac_server.common.domain.BaseCreateTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "chat_message")
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage extends BaseCreateTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "message_id")
    private Long id;

    @Column(name = "chat_room_id", nullable = false)
    private Long chatRoomId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String question;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String answer;

    @Column(columnDefinition = "TEXT")
    private String sourceURL;

    public static ChatMessage from(Long chatRoomId, ChatMessageCreateRequest request) {
        return ChatMessage.builder()
                .chatRoomId(chatRoomId)
                .question(request.getQuestion())
                .answer(request.getAnswer())
                .sourceURL(request.getSourceURL())
                .build();
    }

}
