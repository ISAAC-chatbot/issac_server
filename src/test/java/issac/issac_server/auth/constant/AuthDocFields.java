package issac.issac_server.auth.constant;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static issac.issac_server.document.utils.DocumentLinkGenerator.DocUrl.*;
import static issac.issac_server.document.utils.DocumentLinkGenerator.generateLinkCode;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class AuthDocFields {

    public static final FieldDescriptor[] LOGIN_REQUEST = new FieldDescriptor[]{
            fieldWithPath("provider").type(JsonFieldType.STRING).description(generateLinkCode(OAUTH_PROVIDER_TYPE)),
            fieldWithPath("oauthToken").type(JsonFieldType.STRING).description("OAuth 토큰")
    };

    public static final FieldDescriptor[] LOGIN_RESPONSE = new FieldDescriptor[]{
            fieldWithPath("accessToken").type(JsonFieldType.STRING).description("Access 토큰"),
            fieldWithPath("refreshToken").type(JsonFieldType.STRING).description("Refresh 토큰").optional(),
            fieldWithPath("role").type(JsonFieldType.STRING).description(generateLinkCode(ROLE))
    };

    public static final FieldDescriptor[] REFRESH_TOKEN_REQUEST = new FieldDescriptor[]{
            fieldWithPath("token").type(JsonFieldType.STRING).description("Refresh 토큰")
    };

    public static final FieldDescriptor[] EMAIL_REQUEST = new FieldDescriptor[]{
            fieldWithPath("university").type(JsonFieldType.STRING).description(generateLinkCode(UNIVERSITY)),
            fieldWithPath("email").type(JsonFieldType.STRING).description("이메일 주소")
    };

    public static final FieldDescriptor[] EMAIL_RESPONSE = new FieldDescriptor[]{
            fieldWithPath("verificationCode").type(JsonFieldType.STRING).description("인증 번호"),
    };
}
