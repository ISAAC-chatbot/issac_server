package issac.issac_server.reaction;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import issac.issac_server.RestDocsSupport;
import issac.issac_server.reaction.application.ReactionService;
import issac.issac_server.reaction.application.dto.ReactionCreateRequest;
import issac.issac_server.reaction.presentation.ReactionController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static issac.issac_server.reaction.constant.ReactionDocFields.REACTION_CREATE_REQUEST;
import static issac.issac_server.reaction.constant.ReactionFactory.createMockReactionCreateRequest;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ReactionControllerDocsTest extends RestDocsSupport {

    private final ReactionService reactionService = mock(ReactionService.class);

    @Override
    protected Object initController() {
        return new ReactionController(reactionService);
    }

    @DisplayName("토글(생성 또는 삭제) : 리액션")
    @Test
    void toggle() throws Exception {
        // given
        ReactionCreateRequest request = createMockReactionCreateRequest();

        // when & then
        mockMvc.perform(
                        patch("/api/v1/reactions")
                                .content(objectMapper.writeValueAsString(request))
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("patch-v1-reaction-toggle",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Reaction API")
                                .summary("리액션 토글(생성 또는 삭제)")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .requestFields(REACTION_CREATE_REQUEST)
                                .requestSchema(Schema.schema("ReactionCreateRequest"))
                                .build())));
    }
}