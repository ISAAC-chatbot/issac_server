package issac.issac_server.comment;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import issac.issac_server.RestDocsSupport;
import issac.issac_server.comment.application.CommentService;
import issac.issac_server.comment.application.dto.CommentCreateRequest;
import issac.issac_server.comment.application.dto.CommentResponse;
import issac.issac_server.comment.application.dto.CommentUpdateRequest;
import issac.issac_server.comment.presentation.CommentController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.http.MediaType;

import java.util.List;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static issac.issac_server.comment.constant.CommentDocFields.*;
import static issac.issac_server.comment.constant.CommentFactory.*;
import static issac.issac_server.document.utils.ApiDocumentUtils.SLICE_RESPONSE;
import static issac.issac_server.document.utils.DocumentFormatGenerator.generateFields;
import static issac.issac_server.document.utils.DocumentFormatGenerator.mergeFields;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CommentControllerDocsTest extends RestDocsSupport {

    private final CommentService commentService = mock(CommentService.class);

    @Override
    protected Object initController() {
        return new CommentController(commentService);
    }

    @DisplayName("생성 : 댓글")
    @Test
    void save() throws Exception {

        // given
        CommentCreateRequest request = createMockCommentCreateRequest();
        CommentResponse response = createMockCommentResponse();

        given(commentService.save(any(), any(), any(CommentCreateRequest.class))).willReturn(response);
        // when & then
        mockMvc.perform(
                        post("/api/v1/posts/{postId}/comments", 1L)
                                .content(objectMapper.writeValueAsString(request))
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("post-v1-comment-save",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Comment API")
                                .summary("댓글 작성")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .requestFields(COMMENT_CREATE_REQUEST)
                                .requestSchema(Schema.schema("CommentCreateRequest"))
//                                .responseFields(
//                                        mergeFields(
//                                                SLICE_RESPONSE,
//                                                generateFields("content[].", COMMENT_RESPONSE))
//                                        )
                                .responseFields(COMMENT_RESPONSE)
                                .responseSchema(Schema.schema("CommentResponse"))
                                .build())));
    }

    @DisplayName("검색 : 댓글")
    @Test
    void findComments() throws Exception {
        // given
        List<CommentResponse> responses = createMockCommentResponses();
        Pageable pageable = PageRequest.of(0, 10);
        Slice<CommentResponse> pageResponses = new SliceImpl<>(responses, pageable, false);

        given(commentService.findComments(any(), any(Pageable.class))).willReturn(pageResponses);

        // when & then
        mockMvc.perform(
                        get("/api/v1/posts/{postId}/comments", 1L)
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-v1-comment-findComments",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Comment API")
                                .summary("댓글 검색")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .responseFields(
                                        mergeFields(
                                                SLICE_RESPONSE,
                                                generateFields("content[].", COMMENT_RESPONSE))
                                )
                                .responseSchema(Schema.schema("SliceCommentResponse"))
                                .build())));
    }

    @DisplayName("수정 : 댓글")
    @Test
    void update() throws Exception {

        // given
        CommentUpdateRequest request = createMockCommentUpdateRequest();
        CommentResponse response = createMockCommentResponse();

        given(commentService.update(any(), any(CommentUpdateRequest.class))).willReturn(response);

        // when & then
        mockMvc.perform(
                        put("/api/v1/posts/comments")
                                .content(objectMapper.writeValueAsString(request))
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("put-v1-comment-update",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Comment API")
                                .summary("댓글 수정")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .requestFields(COMMENT_UPDATE_REQUEST)
                                .requestSchema(Schema.schema("CommentUpdateRequest"))
                                .responseFields(COMMENT_RESPONSE)
                                .responseSchema(Schema.schema("CommentResponse"))
                                .build())));
    }

    @DisplayName("삭제 : 댓글")
    @Test
    void remove() throws Exception {
        // given

        // when & then
        mockMvc.perform(
                        delete("/api/v1/posts/comments/{commentId}", 1L)
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent())
                .andDo(document("delete-v1-comment-delete",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Comment API")
                                .summary("댓글 삭제")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .build())));
    }
}