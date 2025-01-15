package issac.issac_server.report.constant;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static issac.issac_server.document.utils.DocumentFormatGenerator.generateFields;
import static issac.issac_server.document.utils.DocumentFormatGenerator.mergeFields;
import static issac.issac_server.document.utils.DocumentLinkGenerator.DocUrl.REPORT_TYPE;
import static issac.issac_server.document.utils.DocumentLinkGenerator.DocUrl.TARGET_TYPE;
import static issac.issac_server.document.utils.DocumentLinkGenerator.generateLinkCode;
import static issac.issac_server.post.constant.PostDocFields.USER_INFO_RESPONSE;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class ReportDocFields {
    public static final FieldDescriptor[] REPORT_CREATE_REQUEST = new FieldDescriptor[]{
            fieldWithPath("targetType").type(JsonFieldType.STRING).description(generateLinkCode(TARGET_TYPE)),
            fieldWithPath("targetId").type(JsonFieldType.STRING).description("대상 ID"),
            fieldWithPath("type").type(JsonFieldType.STRING).description(generateLinkCode(REPORT_TYPE)),
            fieldWithPath("content").type(JsonFieldType.STRING).description("신고 사유")
    };

    public static final FieldDescriptor[] REPORT_PREVIEW_RESPONSE = mergeFields(
            new FieldDescriptor[]{
                    fieldWithPath("type").type(JsonFieldType.STRING).description(generateLinkCode(REPORT_TYPE)),
                    fieldWithPath("targetType").type(JsonFieldType.STRING).description(generateLinkCode(TARGET_TYPE)),
                    fieldWithPath("author").type(JsonFieldType.OBJECT).description("작성자 정보"),
            },
            generateFields("author.", USER_INFO_RESPONSE)
    );

    public static final FieldDescriptor[] REPORT_RESPONSE = mergeFields(
            new FieldDescriptor[]{
                    fieldWithPath("type").type(JsonFieldType.STRING).description(generateLinkCode(REPORT_TYPE)),
                    fieldWithPath("targetType").type(JsonFieldType.STRING).description(generateLinkCode(TARGET_TYPE)),
                    fieldWithPath("targetId").type(JsonFieldType.STRING).description("대상 ID"),
                    fieldWithPath("content").type(JsonFieldType.STRING).description("신고 사유"),
                    fieldWithPath("author").type(JsonFieldType.OBJECT).description("작성자 정보"),
            },
            generateFields("author.", USER_INFO_RESPONSE),
            new FieldDescriptor[]{
                    fieldWithPath("createdAt").type(JsonFieldType.STRING).description("신고 접수 시간"),
            }
    );

}
