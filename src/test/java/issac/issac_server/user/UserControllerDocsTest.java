package issac.issac_server.user;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import issac.issac_server.RestDocsSupport;
import issac.issac_server.user.application.UserService;
import issac.issac_server.user.application.dto.UserCreateRequest;
import issac.issac_server.user.presentation.UserController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static issac.issac_server.user.constant.UserDocFields.USER_CREATE_REQUEST;
import static issac.issac_server.user.constant.UserFactory.createMockUserCreateRequest;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerDocsTest extends RestDocsSupport {

    private final UserService userService = mock(UserService.class);

    @Override
    protected Object initController() {
        return new UserController(userService);
    }

    @DisplayName("회원가입 : 유저")
    @Test
    void signup() throws Exception {
        // given
        UserCreateRequest request = createMockUserCreateRequest();

        // when & then
        mockMvc.perform(
                        post("/api/v1/users/signup")
                                .content(objectMapper.writeValueAsString(request))
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("post-v1-user-signup",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("User API")
                                .summary("회원가입(프로필 등록)")
                                .requestFields(USER_CREATE_REQUEST)
                                .requestSchema(Schema.schema("UserCreateRequest"))
                                .build())));

    }
}