package issac.issac_server.user;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import issac.issac_server.RestDocsSupport;
import issac.issac_server.user.application.UserService;
import issac.issac_server.user.application.dto.SettingResponse;
import issac.issac_server.user.application.dto.UserResponse;
import issac.issac_server.user.domain.SettingType;
import issac.issac_server.user.presentation.UserController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static issac.issac_server.document.utils.DocumentLinkGenerator.DocUrl.SETTING_TYPE;
import static issac.issac_server.document.utils.DocumentLinkGenerator.generateLinkCode;
import static issac.issac_server.user.constant.UserDocFields.SETTING_RESPONSE;
import static issac.issac_server.user.constant.UserDocFields.USER_RESPONSE;
import static issac.issac_server.user.constant.UserFactory.createMockSettingResponse;
import static issac.issac_server.user.constant.UserFactory.createMockUserResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerDocsTest extends RestDocsSupport {

    private final UserService userService = mock(UserService.class);

    @Override
    protected Object initController() {
        return new UserController(userService);
    }

    @DisplayName("정보 조회 : 유저")
    @Test
    void findUser() throws Exception {
        // given
        UserResponse response = createMockUserResponse();

        given(userService.findUser(any())).willReturn(response);

        // when & then
        mockMvc.perform(
                        get("/api/v1/users")
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-v1-user-findUser",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("User API")
                                .summary("유저 정보 조회")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .responseFields(USER_RESPONSE)
                                .responseSchema(Schema.schema("UserResponse"))
                                .build())));

    }

    @DisplayName("설정 조회 : 유저")
    @Test
    void findSetting() throws Exception {

        SettingResponse response = createMockSettingResponse();

        given(userService.findSetting(any(), any(SettingType.class))).willReturn(response);

        // when & then
        mockMvc.perform(
                        get("/api/v1/users/settings/{settingItem}", SettingType.NOTIFICATION)
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-v1-user-findSetting",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("User API")
                                .summary("사용자 설정 조회")
                                .pathParameters(
                                        parameterWithName("settingItem").description(generateLinkCode(SETTING_TYPE))
                                )
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .responseFields(SETTING_RESPONSE)
                                .responseSchema(Schema.schema("SettingResponse"))
                                .build())));

    }

    @DisplayName("설정 토글 : 유저")
    @Test
    void toggleSetting() throws Exception {

        SettingResponse response = createMockSettingResponse();

        given(userService.toggleSetting(any(), any(SettingType.class))).willReturn(response);

        // when & then
        mockMvc.perform(
                        patch("/api/v1/users/settings/{settingItem}", SettingType.NOTIFICATION)
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("patch-v1-user-toggleSetting",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("User API")
                                .summary("사용자 설정 상태 토글")
                                .pathParameters(
                                        parameterWithName("settingItem").description(generateLinkCode(SETTING_TYPE))
                                )
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .responseFields(SETTING_RESPONSE)
                                .responseSchema(Schema.schema("SettingResponse"))
                                .build())));

    }


}