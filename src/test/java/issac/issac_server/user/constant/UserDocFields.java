package issac.issac_server.user.constant;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static issac.issac_server.document.utils.DocumentLinkGenerator.DocUrl.DEGREE_TYPE;
import static issac.issac_server.document.utils.DocumentLinkGenerator.DocUrl.UNIVERSITY;
import static issac.issac_server.document.utils.DocumentLinkGenerator.generateLinkCode;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class UserDocFields {

    public static final FieldDescriptor[] USER_CREATE_REQUEST = new FieldDescriptor[]{
            fieldWithPath("nickname").type(JsonFieldType.STRING).description("닉네임"),
            fieldWithPath("university").type(JsonFieldType.STRING).description(generateLinkCode(UNIVERSITY)),
            fieldWithPath("collegeName").type(JsonFieldType.STRING).description("단과 대학"),
            fieldWithPath("department").type(JsonFieldType.STRING).description("학과"),
            fieldWithPath("degree").type(JsonFieldType.STRING).description(generateLinkCode(DEGREE_TYPE)),
            fieldWithPath("schoolEmail").type(JsonFieldType.STRING).description("학교 이메일"),
            fieldWithPath("marketingConsent").type(JsonFieldType.BOOLEAN).description("광고성 정보 수신 동의 여부")
    };
}
