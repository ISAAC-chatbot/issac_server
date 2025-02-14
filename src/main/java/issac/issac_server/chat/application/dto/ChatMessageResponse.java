package issac.issac_server.chat.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import issac.issac_server.chat.domain.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static issac.issac_server.common.config.Constant.FORMAT_LOCAL_DATE_TIME;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessageResponse {

    private Long id;

    private String question;

    private String answer;

    private String sourceURL;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FORMAT_LOCAL_DATE_TIME)
    private LocalDateTime createdAt;


    public static ChatMessageResponse from(ChatMessage message) {
        return new ChatMessageResponse(
                message.getId(),
                message.getQuestion(),
                message.getAnswer(),
                message.getSourceURL(),
                message.getCreatedDateTime()
        );
    }
}
