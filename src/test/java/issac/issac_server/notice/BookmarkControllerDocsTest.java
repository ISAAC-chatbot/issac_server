package issac.issac_server.notice;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import issac.issac_server.RestDocsSupport;
import issac.issac_server.notice.application.BookmarkService;
import issac.issac_server.notice.application.dto.BookmarkCreateRequest;
import issac.issac_server.notice.application.dto.BookmarkResponse;
import issac.issac_server.notice.presentation.BookmarkController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static issac.issac_server.notice.constant.BookmarkDocFields.BOOKMARK_CREATE_REQUEST;
import static issac.issac_server.notice.constant.BookmarkDocFields.BOOKMARK_RESPONSE;
import static issac.issac_server.notice.constant.BookmarkFactory.createMockBookmarkCreateRequest;
import static issac.issac_server.notice.constant.BookmarkFactory.createMockBookmarkResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookmarkControllerDocsTest extends RestDocsSupport {

    private final BookmarkService bookmarkService = mock(BookmarkService.class);

    @Override
    protected Object initController() {
        return new BookmarkController(bookmarkService);
    }

    @DisplayName("업데이트 : 즐겨찾기")
    @Test
    void update() throws Exception {
        // given
        BookmarkCreateRequest request = createMockBookmarkCreateRequest();
        BookmarkResponse response = createMockBookmarkResponse();

        given(bookmarkService.update(any(), any(BookmarkCreateRequest.class))).willReturn(response);
        // when & then
        mockMvc.perform(
                        patch("/api/v1/notices/bookmarks")
                                .content(objectMapper.writeValueAsString(request))
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("patch-v1-bookmark-update",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Bookmark API")
                                .summary("즐겨찾기 업데이트")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .requestFields(BOOKMARK_CREATE_REQUEST)
                                .requestSchema(Schema.schema("BookmarkCreateRequest"))
                                .responseFields(BOOKMARK_RESPONSE)
                                .responseSchema(Schema.schema("BookmarkResponse"))
                                .build())));
    }

    @DisplayName("검색 : 즐겨찾기")
    @Test
    void search() throws Exception {
        // given
        BookmarkResponse response = createMockBookmarkResponse();

        given(bookmarkService.search(any())).willReturn(response);
        // when & then
        mockMvc.perform(
                        get("/api/v1/notices/bookmarks")
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-v1-bookmark-search",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Bookmark API")
                                .summary("즐겨찾기 검색")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .responseFields(BOOKMARK_RESPONSE)
                                .responseSchema(Schema.schema("BookmarkResponse"))
                                .build())));
    }
}