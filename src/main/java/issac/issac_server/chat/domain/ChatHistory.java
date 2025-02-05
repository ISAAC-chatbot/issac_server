package issac.issac_server.chat.domain;

import issac.issac_server.chat.application.dto.ChatHistoryCreateRequest;
import issac.issac_server.common.domain.BaseCreateTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "chat_history")
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatHistory extends BaseCreateTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "history_id")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String question;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String answer;

    @Column(columnDefinition = "TEXT")
    private String sourceURL;

    public static ChatHistory from(Long userId, ChatHistoryCreateRequest request) {
        return ChatHistory.builder()
                .userId(userId)
                .question(request.getQuestion())
                .answer(request.getAnswer())
                .sourceURL(request.getSourceURL())
                .build();
    }
}
