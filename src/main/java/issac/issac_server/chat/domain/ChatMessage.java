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

    @Column
    private Double elapsedTime;

    public static ChatMessage from(Long chatRoomId, ChatMessageCreateRequest request) {
        return ChatMessage.builder()
                .chatRoomId(chatRoomId)
                .question(request.getQuestion())
                .answer(removeTrailingNewlines(request.getAnswer()))
                .sourceURL(request.getSourceURL())
                .elapsedTime(request.getElapsedTime())
                .build();
    }

    private static String removeTrailingNewlines(String text) {
        if (text == null) {
            return null;
        }
        return text.replaceAll("[\\n\\r]+$", ""); // 문자열 끝부분의 연속된 개행 문자 제거
    }
}
