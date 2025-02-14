package issac.issac_server.chat.constant;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class ChatDocFields {
    public static final FieldDescriptor[] CHAT_MESSAGE_CREATE_REQUEST = new FieldDescriptor[]{
            fieldWithPath("chatRoomId").type(JsonFieldType.NUMBER).description("채팅방 ID").optional(),
            fieldWithPath("question").type(JsonFieldType.STRING).description("질문"),
            fieldWithPath("answer").type(JsonFieldType.STRING).description("답변"),
            fieldWithPath("sourceURL").type(JsonFieldType.STRING).description("출처 사이트 URL").optional(),
    };

    public static final FieldDescriptor[] CHAT_MESSAGE_RESPONSE = new FieldDescriptor[]{
            fieldWithPath("question").type(JsonFieldType.STRING).description("질문"),
            fieldWithPath("answer").type(JsonFieldType.STRING).description("답변"),
            fieldWithPath("sourceURL").type(JsonFieldType.STRING).description("출처 사이트 URL"),
            fieldWithPath("createdAt").type(JsonFieldType.STRING).description("생성 일시"),
    };

    public static final FieldDescriptor[] CHAT_ROOM_RESPONSE = new FieldDescriptor[]{
            fieldWithPath("id").type(JsonFieldType.NUMBER).description("채팅방 ID"),
            fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
            fieldWithPath("createdAt").type(JsonFieldType.STRING).description("생성 일시"),
    };
}
