package issac.issac_server.notice.constant;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static issac.issac_server.document.utils.DocumentLinkGenerator.DocUrl.NOTICE_SOURCE;
import static issac.issac_server.document.utils.DocumentLinkGenerator.generateLinkCode;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class BookmarkDocFields {
    public static final FieldDescriptor[] BOOKMARK_CREATE_REQUEST = new FieldDescriptor[]{
            fieldWithPath("sources[].").type(JsonFieldType.ARRAY).description(generateLinkCode(NOTICE_SOURCE)),
    };

    public static final FieldDescriptor[] BOOKMARK_RESPONSE = new FieldDescriptor[]{
            fieldWithPath("sources[].").type(JsonFieldType.ARRAY).description(generateLinkCode(NOTICE_SOURCE)),
    };

    public static final FieldDescriptor[] BOOKMARK_RESPONSE_V2 = new FieldDescriptor[]{
            fieldWithPath("bookmarkId").type(JsonFieldType.NUMBER).description("북마크 ID"),
            fieldWithPath("source").type(JsonFieldType.STRING).description(generateLinkCode(NOTICE_SOURCE)),
            fieldWithPath("notificationConsent").type(JsonFieldType.BOOLEAN).description("알림 동의 여부")
    };

}
