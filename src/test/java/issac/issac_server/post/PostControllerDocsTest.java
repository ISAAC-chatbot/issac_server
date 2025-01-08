package issac.issac_server.post;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import issac.issac_server.RestDocsSupport;
import issac.issac_server.post.application.PostService;
import issac.issac_server.post.application.PostUpdateRequest;
import issac.issac_server.post.application.dto.PostCreateRequest;
import issac.issac_server.post.application.dto.PostPreviewResponse;
import issac.issac_server.post.application.dto.PostResponse;
import issac.issac_server.post.application.dto.PostSearchCondition;
import issac.issac_server.post.presentation.PostController;
import issac.issac_server.reaction.domain.ReactionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.restdocs.request.ParameterDescriptor;

import java.util.List;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static issac.issac_server.document.utils.ApiDocumentUtils.*;
import static issac.issac_server.document.utils.DocumentFormatGenerator.*;
import static issac.issac_server.document.utils.DocumentLinkGenerator.DocUrl.REACTION_TYPE;
import static issac.issac_server.document.utils.DocumentLinkGenerator.generateLinkCode;
import static issac.issac_server.post.constant.PostDocFields.*;
import static issac.issac_server.post.constant.PostFactory.*;
import static issac.issac_server.reaction.domain.ReactionType.LIKE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PostControllerDocsTest extends RestDocsSupport {

    private final PostService postService = mock(PostService.class);

    @Override
    protected Object initController() {
        return new PostController(postService);
    }

    @DisplayName("생성 : 게시글")
    @Test
    void savePost() throws Exception {
        // given
        PostCreateRequest request = createMockPostCreateRequest();
        PostResponse response = createMockPostResponse();

        given(postService.save(any(), any(PostCreateRequest.class))).willReturn(response);
        // when & then
        mockMvc.perform(
                        post("/api/v1/posts")
                                .content(objectMapper.writeValueAsString(request))
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("post-v1-post-savePost",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Post API")
                                .summary("게시글 작성")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .requestFields(POST_CREATE_REQUEST)
                                .requestSchema(Schema.schema("PostCreateRequest"))
                                .responseFields(POST_RESPONSE)
                                .responseSchema(Schema.schema("PostResponse"))
                                .build())));
    }

    @DisplayName("조회 : 게시글")
    @Test
    void findPost() throws Exception {
        // given
        PostResponse response = createMockPostResponseWithReaction();

        given(postService.find(any(), any())).willReturn(response);

        // when & then
        mockMvc.perform(
                        get("/api/v1/posts/{postId}", 1L)
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-v1-post-findPost",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Post API")
                                .summary("게시글 조회")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .responseFields(POST_RESPONSE_WITH_REACTION)
                                .responseSchema(Schema.schema("PostResponse"))
                                .build())));
    }

    @DisplayName("수정 : 게시글")
    @Test
    void updatePost() throws Exception {

        // given
        PostUpdateRequest request = createMockPostUpdateRequest();
        PostResponse response = createMockPostResponseWithReaction();

        given(postService.update(any(), any(), any(PostUpdateRequest.class))).willReturn(response);

        // when & then
        mockMvc.perform(
                        put("/api/v1/posts/{postId}", 1L)
                                .content(objectMapper.writeValueAsString(request))
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-v1-post-updatePost",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Post API")
                                .summary("게시글 수정")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .requestFields(POST_UPDATE_REQUEST)
                                .requestSchema(Schema.schema("PostUpdateRequest"))
                                .responseFields(POST_RESPONSE_WITH_REACTION)
                                .responseSchema(Schema.schema("PostResponse"))
                                .build())));
    }

    @DisplayName("검색 : 게시글")
    @Test
    void findPosts() throws Exception {
        // given
        List<PostPreviewResponse> responses = createMockPostPreviewResponses();
        Pageable pageable = PageRequest.of(0, 10);
        Page<PostPreviewResponse> pageResponses = new PageImpl<>(responses, pageable, responses.size());
        given(postService.findPosts(any(PostSearchCondition.class), any(Pageable.class))).willReturn(pageResponses);
        // when & then
        mockMvc.perform(
                        get("/api/v1/posts")
                                .param("keyword", "복학")
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-v1-post-findPosts",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Post API")
                                .summary("게시글 검색")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .queryParameters(mergeParameters(
                                        new ParameterDescriptor[]{
                                                pageParam(),
                                                sizeParam(),
                                        },
                                        POST_SEARCH_CONDITION
                                ))
                                .responseFields(mergeFields(
                                        PAGE_RESPONSE,
                                        generateFields("content[].", POST_PREVIEW_RESPONSE)
                                ))
                                .responseSchema(Schema.schema("PostPreviewResponse"))
                                .build())));
    }

    @DisplayName("내 게시글 검색 : 게시글")
    @Test
    void findMyPosts() throws Exception {
        // given
        List<PostPreviewResponse> responses = createMockPostPreviewResponses();
        Pageable pageable = PageRequest.of(0, 10);
        Page<PostPreviewResponse> pageResponses = new PageImpl<>(responses, pageable, responses.size());
        given(postService.findMyPosts(any(), any(Pageable.class))).willReturn(pageResponses);
        // when & then
        mockMvc.perform(
                        get("/api/v1/posts/me")
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-v1-post-findMyPosts",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Post API")
                                .summary("내 게시글 검색")
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
                                        generateFields("content[].", POST_PREVIEW_RESPONSE)
                                ))
                                .responseSchema(Schema.schema("PostPreviewResponse"))
                                .build())));
    }

    @DisplayName("반응한 게시글 검색 : 게시글")
    @Test
    void findPostsByReaction() throws Exception {
        // given
        List<PostPreviewResponse> responses = createMockPostPreviewResponses();
        Pageable pageable = PageRequest.of(0, 10);
        Page<PostPreviewResponse> pageResponses = new PageImpl<>(responses, pageable, responses.size());
        given(postService.findPostsByReaction(any(), any(ReactionType.class), any(Pageable.class))).willReturn(pageResponses);
        // when & then
        mockMvc.perform(
                        get("/api/v1/posts/me/reactions")
                                .param("reactionType", LIKE.toString())
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-v1-post-findPostsByReaction",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Post API")
                                .summary("반응한 게시글 검색")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .queryParameters(
                                        pageParam(),
                                        sizeParam(),
                                        parameterWithName("reactionType").description(generateLinkCode(REACTION_TYPE))
                                )
                                .responseFields(mergeFields(
                                        PAGE_RESPONSE,
                                        generateFields("content[].", POST_PREVIEW_RESPONSE)
                                ))
                                .responseSchema(Schema.schema("PostPreviewResponse"))
                                .build())));
    }
}