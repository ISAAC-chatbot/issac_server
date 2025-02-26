package issac.issac_server.user.constant;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static issac.issac_server.document.utils.DocumentLinkGenerator.DocUrl.EDUCATION_LEVEL;
import static issac.issac_server.document.utils.DocumentLinkGenerator.DocUrl.UNIVERSITY;
import static issac.issac_server.document.utils.DocumentLinkGenerator.generateLinkCode;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class ProfileDocFields {

    public static final FieldDescriptor[] PROFILE_RESPONSE = new FieldDescriptor[]{
            fieldWithPath("nickname").type(JsonFieldType.STRING).description("닉네임"),
            fieldWithPath("university").type(JsonFieldType.STRING).description(generateLinkCode(UNIVERSITY)),
            fieldWithPath("educationLevel").type(JsonFieldType.STRING).description(generateLinkCode(EDUCATION_LEVEL)),
            fieldWithPath("collegeName").type(JsonFieldType.STRING).description("단과 대학"),
            fieldWithPath("department").type(JsonFieldType.STRING).description("학과"),
            fieldWithPath("schoolEmail").type(JsonFieldType.STRING).description("학교 이메일"),
            fieldWithPath("profilePhotoUrl").type(JsonFieldType.STRING).description("프로필 사진 URL")
    };

    public static final FieldDescriptor[] PROFILE_UPDATE_REQUEST = new FieldDescriptor[]{
            fieldWithPath("nickname").type(JsonFieldType.STRING).description("닉네임").optional(),
            fieldWithPath("educationLevel").type(JsonFieldType.STRING).description(generateLinkCode(EDUCATION_LEVEL)).optional(),
            fieldWithPath("collegeName").type(JsonFieldType.STRING).description("단과 대학").optional(),
            fieldWithPath("department").type(JsonFieldType.STRING).description("학과").optional(),
            fieldWithPath("profilePhotoUrl").type(JsonFieldType.STRING).description("프로필 사진 URL").optional()
    };
}
