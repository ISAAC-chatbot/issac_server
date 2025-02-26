package issac.issac_server.user;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import issac.issac_server.RestDocsSupport;
import issac.issac_server.user.application.dto.ProfileCreateRequest;
import issac.issac_server.user.application.dto.ProfileResponse;
import issac.issac_server.user.application.dto.ProfileUpdateRequest;
import issac.issac_server.user.application.dto.UserResponse;
import issac.issac_server.user.application.profile.ProfileService;
import issac.issac_server.user.presentation.ProfileController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static issac.issac_server.user.constant.ProfileDocFields.*;
import static issac.issac_server.user.constant.ProfileFactory.*;
import static issac.issac_server.user.constant.UserDocFields.USER_RESPONSE;
import static issac.issac_server.user.constant.UserFactory.createMockUserResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ProfileControllerDocsTest extends RestDocsSupport {

    private final ProfileService profileService = mock(ProfileService.class);

    @Override
    protected Object initController() {
        return new ProfileController(profileService);
    }

    @DisplayName("내 프로필 조회 : 프로필")
    @Test
    void findMyProfile() throws Exception {

        // given
        ProfileResponse response = createMockProfileResponse();

        given(profileService.findProfile(any())).willReturn(response);

        // when & then
        mockMvc.perform(
                        get("/api/v1/users/profile")
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-v1-profile-findMyProfile",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Profile API")
                                .summary("내 프로필 조회")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .responseFields(PROFILE_RESPONSE)
                                .responseSchema(Schema.schema("ProfileResponse"))
                                .build())));

    }

    @DisplayName("등록 : 프로필")
    @Test
    void saveProfile() throws Exception {
        // given
        ProfileCreateRequest request = createMockProfileCreateRequest();
        UserResponse response = createMockUserResponse();

        given(profileService.saveProfile(any(), any(ProfileCreateRequest.class))).willReturn(response);

        // when & then
        mockMvc.perform(
                        post("/api/v1/users/profile")
                                .content(objectMapper.writeValueAsString(request))
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("post-v1-profile-saveProfile",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Profile API")
                                .summary("프로필 등록(회원 가입)")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .requestFields(PROFILE_CREATE_REQUEST)
                                .requestSchema(Schema.schema("ProfileCreateRequest"))
                                .responseFields(USER_RESPONSE)
                                .responseSchema(Schema.schema("UserResponse"))
                                .build())));

    }

    @DisplayName("내 프로필 업데이트 : 프로필")
    @Test
    void updateMyProfile() throws Exception {

        // given
        ProfileUpdateRequest request = createMockProfileUpdateRequest();
        ProfileResponse response = createMockProfileResponse();

        given(profileService.updateMyProfile(any(), any(ProfileUpdateRequest.class))).willReturn(response);

        // when & then
        mockMvc.perform(
                        patch("/api/v1/users/profile")
                                .content(objectMapper.writeValueAsString(request))
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("patch-v1-profile-updateMyProfile",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Profile API")
                                .summary("내 프로필 업데이트")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .requestFields(PROFILE_UPDATE_REQUEST)
                                .requestSchema(Schema.schema("ProfileUpdateRequest"))
                                .responseFields(PROFILE_RESPONSE)
                                .responseSchema(Schema.schema("ProfileResponse"))
                                .build())));

    }
}