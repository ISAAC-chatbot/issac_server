package issac.issac_server.chat;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import issac.issac_server.RestDocsSupport;
import issac.issac_server.chat.application.ChatService;
import issac.issac_server.chat.application.dto.ChatHistoryCreateRequest;
import issac.issac_server.chat.application.dto.ChatHistoryResponse;
import issac.issac_server.chat.presentation.ChatController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;

import java.util.List;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static issac.issac_server.chat.constant.ChatDocFields.CHAT_HISTORY_CREATE_REQUEST;
import static issac.issac_server.chat.constant.ChatDocFields.CHAT_HISTORY_RESPONSE;
import static issac.issac_server.chat.constant.ChatFactory.createMockChatHistoryCreateRequest;
import static issac.issac_server.chat.constant.ChatFactory.createMockChatHistoryResponses;
import static issac.issac_server.document.utils.ApiDocumentUtils.*;
import static issac.issac_server.document.utils.DocumentFormatGenerator.generateFields;
import static issac.issac_server.document.utils.DocumentFormatGenerator.mergeFields;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ChatControllerDocsTest extends RestDocsSupport {

    private final ChatService chatService = mock(ChatService.class);

    @Override
    protected Object initController() {
        return new ChatController(chatService);
    }

    @DisplayName("목록 조회 : 챗봇 히스토리")
    @Test
    void findHistories() throws Exception {
        // given
        List<ChatHistoryResponse> responses = createMockChatHistoryResponses();
        Pageable pageable = PageRequest.of(0, 10);
        Page<ChatHistoryResponse> pageResponses = new PageImpl<>(responses, pageable, responses.size());

        given(chatService.findHistories(any(), any(Pageable.class))).willReturn(pageResponses);

        // when & then
        mockMvc.perform(
                        get("/api/v1/chat/histories")
                                .param("page", "0")
                                .param("size", "10")
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-v1-chat-findHistories",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Chat API")
                                .summary("챗봇 히스토리 목록 조회")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .queryParameters(
                                        pageParam(),
                                        sizeParam()
                                )
                                .responseFields(mergeFields(
                                        PAGE_RESPONSE,
                                        generateFields("content[].", CHAT_HISTORY_RESPONSE)
                                ))
                                .responseSchema(Schema.schema("ChatHistoryResponse"))
                                .build())));
    }

    @DisplayName("생성 : 챗봇 히스토리")
    @Test
    void saveHistory() throws Exception {
        // given
        ChatHistoryCreateRequest request = createMockChatHistoryCreateRequest();

        // when & then
        mockMvc.perform(
                        post("/api/v1/chat/histories")
                                .content(objectMapper.writeValueAsString(request))
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("post-v1-chat-saveHistory",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Chat API")
                                .summary("챗봇 히스토리 생성")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .requestFields(CHAT_HISTORY_CREATE_REQUEST)
                                .requestSchema(Schema.schema("ChatHistoryCreateRequest"))
                                .build())));
    }
}