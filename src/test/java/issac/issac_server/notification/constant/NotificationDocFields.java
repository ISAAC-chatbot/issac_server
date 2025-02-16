package issac.issac_server.notification.constant;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;

import static issac.issac_server.document.utils.DocumentLinkGenerator.DocUrl.NOTIFICATION_TYPE;
import static issac.issac_server.document.utils.DocumentLinkGenerator.DocUrl.TARGET_TYPE;
import static issac.issac_server.document.utils.DocumentLinkGenerator.generateLinkCode;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public class NotificationDocFields {

    public static final FieldDescriptor[] NOTIFICATION_RESPONSE = new FieldDescriptor[]{
            fieldWithPath("id").type(JsonFieldType.NUMBER).description("알림 ID"),
            fieldWithPath("notificationType").type(JsonFieldType.STRING).description(generateLinkCode(NOTIFICATION_TYPE)),
            fieldWithPath("title").type(JsonFieldType.STRING).description("키워드나 북마크 이름 또는 게시글 제목"),
            fieldWithPath("content").type(JsonFieldType.STRING).description("글 제목 및 댓글 내용"),
            fieldWithPath("entityType").type(JsonFieldType.STRING).description(generateLinkCode(TARGET_TYPE)),
            fieldWithPath("entityId").type(JsonFieldType.STRING).description("대상 ID"),
            fieldWithPath("author").type(JsonFieldType.STRING).description("작성자 정보"),
            fieldWithPath("createdAt").type(JsonFieldType.STRING).description("알림 생성 시간"),
            fieldWithPath("read").type(JsonFieldType.BOOLEAN).description("읽음 여부")
    };

    public static final ParameterDescriptor[] NOTIFICATION_SEARCH_CONDITION = new ParameterDescriptor[]{
            parameterWithName("notificationType").description(generateLinkCode(NOTIFICATION_TYPE)).optional(),
            parameterWithName("entityType").description(generateLinkCode(TARGET_TYPE)).optional(),
            parameterWithName("read").description("읽음 여부").optional()
    };

    public static final FieldDescriptor[] NOTIFICATION_UPDATE_REQUEST = new FieldDescriptor[]{
            fieldWithPath("notificationType").type(JsonFieldType.STRING).description(generateLinkCode(NOTIFICATION_TYPE)),
            fieldWithPath("entityType").type(JsonFieldType.STRING).description(generateLinkCode(TARGET_TYPE)),
            fieldWithPath("entityId").type(JsonFieldType.STRING).description("대상 ID"),
    };


}
