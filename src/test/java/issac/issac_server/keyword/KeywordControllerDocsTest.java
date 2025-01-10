package issac.issac_server.keyword;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import issac.issac_server.RestDocsSupport;
import issac.issac_server.keyword.application.KeywordService;
import issac.issac_server.keyword.application.dto.KeywordRequest;
import issac.issac_server.keyword.application.dto.KeywordResponse;
import issac.issac_server.keyword.presentation.KeywordController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static issac.issac_server.document.utils.DocumentFormatGenerator.generateFields;
import static issac.issac_server.keyword.constant.KeywordDocFields.KEYWORD_REQUEST;
import static issac.issac_server.keyword.constant.KeywordDocFields.KEYWORD_RESPONSE;
import static issac.issac_server.keyword.constant.KeywordFactory.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class KeywordControllerDocsTest extends RestDocsSupport {

    private final KeywordService keywordService = mock(KeywordService.class);

    @Override
    protected Object initController() {
        return new KeywordController(keywordService);
    }

    @DisplayName("생성 : 키워드")
    @Test
    void save() throws Exception {

        // given
        KeywordRequest request = createMockKeywordRequest();
        KeywordResponse response = createMockKeywordResponse();

        given(keywordService.save(any(), any(KeywordRequest.class))).willReturn(response);

        // when & then
        mockMvc.perform(
                        post("/api/v1/keywords")
                                .content(objectMapper.writeValueAsString(request))
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("post-v1-keyword-save",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Keyword API")
                                .summary("키워드 작성")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .requestFields(KEYWORD_REQUEST)
                                .requestSchema(Schema.schema("KeywordRequest"))
                                .responseFields(KEYWORD_RESPONSE)
                                .responseSchema(Schema.schema("KeywordResponse"))
                                .build())));
    }

    @DisplayName("생성 : 검색")
    @Test
    void findKeywords() throws Exception {
        // given
        List<KeywordResponse> responses = createMockKeywordResponses();
        given(keywordService.findKeywords(any())).willReturn(responses);

        // when & then
        mockMvc.perform(
                        get("/api/v1/keywords")
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-v1-keyword-findKeywords",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Keyword API")
                                .summary("키워드 검색")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .responseFields(generateFields("[].",KEYWORD_RESPONSE))
                                .responseSchema(Schema.schema("KeywordResponses"))
                                .build())));
    }

    @DisplayName("삭제 : 키워드")
    @Test
    void remove() throws Exception {

        // when & then
        mockMvc.perform(
                        delete("/api/v1/keywords/{keywordId}",1L)
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent())
                .andDo(document("delete-v1-keyword-remove",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Keyword API")
                                .summary("키워드 삭제")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .build())));
    }
}