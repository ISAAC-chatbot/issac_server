package issac.issac_server.chat.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import issac.issac_server.chat.domain.ChatRoom;
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
public class ChatRoomInfoResponse {

    private Long id;

    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FORMAT_LOCAL_DATE_TIME)
    private LocalDateTime createdAt;

    public static ChatRoomInfoResponse from (ChatRoom chatRoom) {
        return new ChatRoomInfoResponse(chatRoom.getId(), chatRoom.getTitle(), chatRoom.getCreatedDateTime());
    }
}
