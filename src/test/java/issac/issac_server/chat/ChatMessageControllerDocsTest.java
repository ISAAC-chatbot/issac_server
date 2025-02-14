package issac.issac_server.chat;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import issac.issac_server.RestDocsSupport;
import issac.issac_server.chat.application.ChatService;
import issac.issac_server.chat.application.dto.ChatMessageCreateRequest;
import issac.issac_server.chat.application.dto.ChatMessageResponse;
import issac.issac_server.chat.presentation.ChatMessageController;
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
import static issac.issac_server.chat.constant.ChatDocFields.CHAT_MESSAGE_CREATE_REQUEST;
import static issac.issac_server.chat.constant.ChatDocFields.CHAT_MESSAGE_RESPONSE;
import static issac.issac_server.chat.constant.ChatFactory.createMockChatMessageCreateRequest;
import static issac.issac_server.chat.constant.ChatFactory.createMockChatMessageResponses;
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

class ChatMessageControllerDocsTest extends RestDocsSupport {

    private final ChatService chatService = mock(ChatService.class);

    @Override
    protected Object initController() {
        return new ChatMessageController(chatService);
    }

    @DisplayName("기록 조회 : 챗봇 메시지")
    @Test
    void findMessages() throws Exception {
        // given
        List<ChatMessageResponse> responses = createMockChatMessageResponses();
        Pageable pageable = PageRequest.of(0, 10);
        Page<ChatMessageResponse> pageResponses = new PageImpl<>(responses, pageable, responses.size());

        given(chatService.findMessages(any(), any(), any(Pageable.class))).willReturn(pageResponses);

        // when & then
        mockMvc.perform(
                        get("/api/v1/chat/{chatRoomId}/messages", 1L)
                                .param("page", "0")
                                .param("size", "10")
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-v1-chatMessage-findMessages",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("ChatBot Message API")
                                .summary("챗봇 메시지 기록 조회")
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
                                        generateFields("content[].", CHAT_MESSAGE_RESPONSE)
                                ))
                                .responseSchema(Schema.schema("ChatMessageResponse"))
                                .build())));
    }

    @DisplayName("기록 생성 : 챗봇 메시지")
    @Test
    void saveMessage() throws Exception {
        // given
        ChatMessageCreateRequest request = createMockChatMessageCreateRequest();

        // when & then
        mockMvc.perform(
                        post("/api/v1/chat/messages")
                                .content(objectMapper.writeValueAsString(request))
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("post-v1-chatMessage-saveMessage",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("ChatBot Message API")
                                .summary("챗봇 메시지 기록 생성")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .requestFields(CHAT_MESSAGE_CREATE_REQUEST)
                                .requestSchema(Schema.schema("ChatMessageCreateRequest"))
                                .build())));
    }

}