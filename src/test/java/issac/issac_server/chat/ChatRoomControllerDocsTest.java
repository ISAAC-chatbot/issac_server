package issac.issac_server.chat;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import issac.issac_server.RestDocsSupport;
import issac.issac_server.chat.application.ChatService;
import issac.issac_server.chat.application.dto.ChatRoomResponse;
import issac.issac_server.chat.presentation.ChatRoomController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static issac.issac_server.chat.constant.ChatDocFields.CHAT_ROOM_RESPONSE;
import static issac.issac_server.chat.constant.ChatFactory.createMockChatRoomResponses;
import static issac.issac_server.document.utils.ApiDocumentUtils.*;
import static issac.issac_server.document.utils.DocumentFormatGenerator.generateFields;
import static issac.issac_server.document.utils.DocumentFormatGenerator.mergeFields;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ChatRoomControllerDocsTest extends RestDocsSupport {

    private final ChatService chatService = mock(ChatService.class);

    @Override
    protected Object initController() {
        return new ChatRoomController(chatService);
    }

    @DisplayName("목록 조회 : 챗봇 채팅방")
    @Test
    void findChatRooms() throws Exception {
        // given
        List<ChatRoomResponse> responses = createMockChatRoomResponses();
        Pageable pageable = PageRequest.of(0, 10);
        Page<ChatRoomResponse> pageResponses = new PageImpl<>(responses, pageable, responses.size());

        given(chatService.findChatRooms(any(), any(Pageable.class))).willReturn(pageResponses);

        // when & then
        mockMvc.perform(
                        get("/api/v1/chat")
                                .param("page", "0")
                                .param("size", "10")
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-v1-chatRoom-findChatRooms",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("ChatBot Room API")
                                .summary("챗봇 채팅방 목록 조회")
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
                                        generateFields("content[].", CHAT_ROOM_RESPONSE)
                                ))
                                .build())));
    }

    @DisplayName("삭제 : 챗봇 채팅방")
    @Test
    void deleteChatRoom() throws Exception {

        // when & then
        mockMvc.perform(
                        delete("/api/v1/chat/{chatRoomId}", 1L)
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                )
                .andDo(print())
                .andExpect(status().isNoContent())
                .andDo(document("delete-v1-chatRoom-deleteChatRoom",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("ChatBot Room API")
                                .summary("챗봇 채팅방 삭제")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .build())));
    }
}