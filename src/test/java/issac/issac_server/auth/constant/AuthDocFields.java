package issac.issac_server.auth.constant;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static issac.issac_server.document.utils.DocumentLinkGenerator.DocUrl.OAUTH_PROVIDER_TYPE;
import static issac.issac_server.document.utils.DocumentLinkGenerator.DocUrl.ROLE;
import static issac.issac_server.document.utils.DocumentLinkGenerator.generateLinkCode;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class AuthDocFields {

    public static final FieldDescriptor[] LOGIN_REQUEST = new FieldDescriptor[]{
            fieldWithPath("provider").type(JsonFieldType.STRING).description(generateLinkCode(OAUTH_PROVIDER_TYPE)),
            fieldWithPath("oauthToken").type(JsonFieldType.STRING).description("OAuth 토큰")
    };

    public static final FieldDescriptor[] LOGIN_RESPONSE = new FieldDescriptor[]{
            fieldWithPath("accessToken").type(JsonFieldType.STRING).description("Access 토큰"),
            fieldWithPath("refreshToken").type(JsonFieldType.STRING).description("Refresh 토큰"),
            fieldWithPath("role").type(JsonFieldType.STRING).description(generateLinkCode(ROLE))
    };
}
