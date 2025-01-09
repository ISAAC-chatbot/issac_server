package issac.issac_server.auth;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import issac.issac_server.RestDocsSupport;
import issac.issac_server.auth.application.AuthFacadeService;
import issac.issac_server.auth.application.dto.*;
import issac.issac_server.auth.domain.OAuthProviderType;
import issac.issac_server.auth.presentation.AuthController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static issac.issac_server.auth.constant.AuthDocFields.*;
import static issac.issac_server.auth.constant.AuthFactory.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

    @DisplayName("토큰 재발급 : 인증")
    @Test
    void refresh() throws Exception {

        // given
        RefreshTokenRequest request = createMockRefreshTokenRequest();
        LoginResponse response = createMockLoginResponse();

        given(authFacadeService.refresh(any(RefreshTokenRequest.class))).willReturn(response);

        // when & then
        mockMvc.perform(
                        post("/api/v1/auth/refresh")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("post-v1-auth-refresh",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Auth API")
                                .summary("토큰 재발급")
                                .requestFields(REFRESH_TOKEN_REQUEST)
                                .responseFields(LOGIN_RESPONSE)
                                .requestSchema(Schema.schema("RefreshTokenRequest"))
                                .responseSchema(Schema.schema("LoginResponse"))
                                .build())));

    }

    @DisplayName("이메일 인증번호 요청 : 인증")
    @Test
    void sendEmailVerification() throws Exception {

        // given
        EmailRequest request = createMockEmailRequest();
        EmailResponse response = createMockEmailResponse();

        given(authFacadeService.sendEmailVerification(any(EmailRequest.class))).willReturn(response);

        // when & then
        mockMvc.perform(
                        post("/api/v1/auth/email")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("post-v1-auth-email",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Auth API")
                                .summary("이메일 인증번호 요청")
                                .requestFields(EMAIL_REQUEST)
                                .responseFields(EMAIL_RESPONSE)
                                .requestSchema(Schema.schema("EmailRequest"))
                                .responseSchema(Schema.schema("EmailResponse"))
                                .build())));

    }

    @DisplayName("탈퇴 : 인증")
    @Test
    void revoke() throws Exception {

        // when & then
        mockMvc.perform(
                        delete("/api/v1/auth/revoke")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent())
                .andDo(document("delete-v1-auth-revoke",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Auth API")
                                .summary("계정 탈퇴")
                                .build())));

    }
}