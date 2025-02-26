package issac.issac_server.user.constant;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static issac.issac_server.document.utils.DocumentLinkGenerator.DocUrl.*;
import static issac.issac_server.document.utils.DocumentLinkGenerator.generateLinkCode;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class UserDocFields {

    public static final FieldDescriptor[] SETTING_RESPONSE = new FieldDescriptor[]{
            fieldWithPath("type").type(JsonFieldType.STRING).description(generateLinkCode(SETTING_TYPE)),
            fieldWithPath("active").type(JsonFieldType.BOOLEAN).description("동의 여부"),
    };

    public static final FieldDescriptor[] USER_RESPONSE = new FieldDescriptor[]{
            fieldWithPath("id").type(JsonFieldType.NUMBER).description("유저 ID"),
            fieldWithPath("oauthProvider").type(JsonFieldType.STRING).description(generateLinkCode(OAUTH_PROVIDER_TYPE)),
            fieldWithPath("email").type(JsonFieldType.STRING).description("가입 이메일"),
            fieldWithPath("role").type(JsonFieldType.STRING).description(generateLinkCode(ROLE)),
            fieldWithPath("marketingConsent").type(JsonFieldType.BOOLEAN).description("광고성 정보 수신 동의 여부")
    };
}
