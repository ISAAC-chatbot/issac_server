package issac.issac_server.auth;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import issac.issac_server.RestDocsSupport;
import issac.issac_server.auth.application.AuthFacadeService;
import issac.issac_server.auth.application.dto.LoginRequest;
import issac.issac_server.auth.application.dto.LoginResponse;
import issac.issac_server.auth.domain.OAuthProviderType;
import issac.issac_server.auth.presentation.AuthController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static issac.issac_server.auth.constant.AuthDocFields.LOGIN_REQUEST;
import static issac.issac_server.auth.constant.AuthDocFields.LOGIN_RESPONSE;
import static issac.issac_server.auth.constant.AuthFactory.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthControllerDocsTest extends RestDocsSupport {

    private final AuthFacadeService authFacadeService = mock(AuthFacadeService.class);

    @Override
    protected Object initController() {
        return new AuthController(authFacadeService);
    }

    @DisplayName("로그인 : 인증")
    @Test
    void login() throws Exception {

        // given
        LoginRequest request = createMockLoginRequest(OAuthProviderType.KAKAO, "mock-access-token-43210");
        LoginResponse response = createMockLoginResponse();

        given(authFacadeService.login(any(LoginRequest.class))).willReturn(response);

        // when & then
        mockMvc.perform(
                        post("/api/v1/auth/login")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("post-v1-auth-login",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Auth API")
                                .summary("소셜 로그인")
                                .requestFields(LOGIN_REQUEST)
                                .responseFields(LOGIN_RESPONSE)
                                .requestSchema(Schema.schema("LoginRequest"))
                                .responseSchema(Schema.schema("LoginResponse"))
                                .build())));

    }

    @DisplayName("게스트 로그인 : 인증")
    @Test
    void guestLogin() throws Exception {

        // given
        LoginResponse response = createMockGuestLoginResponse();

        given(authFacadeService.guestLogin()).willReturn(response);

        // when & then
        mockMvc.perform(
                        post("/api/v1/auth/guest-login")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("post-v1-auth-guestLogin",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Auth API")
                                .summary("게스트 로그인")
                                .responseFields(LOGIN_RESPONSE)
                                .responseSchema(Schema.schema("LoginResponse"))
                                .build())));

    }
}