package issac.issac_server.notice;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import issac.issac_server.RestDocsSupport;
import issac.issac_server.notice.application.BookmarkService;
import issac.issac_server.notice.application.dto.request.BookmarkCreateRequest;
import issac.issac_server.notice.application.dto.request.BookmarkUpdateRequest;
import issac.issac_server.notice.application.dto.response.BookmarkResponseV2;
import issac.issac_server.notice.domain.NoticeSource;
import issac.issac_server.notice.presentation.BookmarkControllerV2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static issac.issac_server.document.utils.DocumentFormatGenerator.generateFields;
import static issac.issac_server.document.utils.DocumentLinkGenerator.DocUrl.NOTICE_SOURCE;
import static issac.issac_server.document.utils.DocumentLinkGenerator.generateLinkCode;
import static issac.issac_server.notice.constant.BookmarkDocFields.*;
import static issac.issac_server.notice.constant.BookmarkFactory.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookmarkControllerV2DocsTest extends RestDocsSupport {

    private final BookmarkService bookmarkService = mock(BookmarkService.class);

    @Override
    protected Object initController() {
        return new BookmarkControllerV2(bookmarkService);
    }

    @DisplayName("생성 : 즐겨찾기")
    @Test
    void save() throws Exception {
        // given
        BookmarkCreateRequest request = createMockBookmarkCreateRequest();

        BookmarkResponseV2 response = createMockBookmarkResponseV2();

        given(bookmarkService.save(any(), any(NoticeSource.class))).willReturn(response);
        // when & then
        mockMvc.perform(
                        post("/api/v2/notices/bookmarks")
                                .content(objectMapper.writeValueAsString(request))
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("post-v2-bookmark-save",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Bookmark API V2")
                                .summary("즐겨찾기 생성")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .requestFields(BOOKMARK_CREATE_REQUEST)
                                .requestSchema(Schema.schema("BookmarkCreateRequest"))
                                .responseFields(BOOKMARK_RESPONSE_V2)
                                .responseSchema(Schema.schema("BookmarkResponseV2"))
                                .build())));
    }

    @DisplayName("삭제 : 즐겨찾기")
    @Test
    void deleteBookmark() throws Exception {

        // when & then
        mockMvc.perform(
                        delete("/api/v2/notices/bookmarks")
                                .queryParam("source", NoticeSource.ACADEMIC_NOTICE.toString())
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                )
                .andDo(print())
                .andExpect(status().isNoContent())
                .andDo(document("delete-v2-bookmark-delete",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Bookmark API V2")
                                .summary("즐겨찾기 삭제")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .queryParameters(
                                        parameterWithName("source").description(generateLinkCode(NOTICE_SOURCE))
                                )
                                .build())));
    }

    @DisplayName("업데이트 V2 : 즐겨찾기")
    @Test
    void update() throws Exception {
        // given
        BookmarkUpdateRequest request = createMockBookmarkUpdateRequest();
        List<BookmarkResponseV2> responses = createMockBookmarkResponsesV2();

        given(bookmarkService.update(any(), any(BookmarkUpdateRequest.class))).willReturn(responses);
        // when & then
        mockMvc.perform(
                        put("/api/v2/notices/bookmarks")
                                .content(objectMapper.writeValueAsString(request))
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("put-v2-bookmark-update",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Bookmark API V2")
                                .summary("즐겨찾기 전체 업데이트")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .requestFields(BOOKMARK_UPDATE_REQUEST)
                                .requestSchema(Schema.schema("BookmarkUpdateRequest"))
                                .responseFields(generateFields("[].", BOOKMARK_RESPONSE_V2))
                                .build())));
    }

    @DisplayName("검색 : 즐겨찾기")
    @Test
    void search() throws Exception {
        // given

        List<BookmarkResponseV2> responses = createMockBookmarkResponsesV2();
        given(bookmarkService.findBookmarks(any())).willReturn(responses);
        // when & then
        mockMvc.perform(
                        get("/api/v2/notices/bookmarks")
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-v2-bookmark-search",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Bookmark API V2")
                                .summary("즐겨찾기 검색")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .responseFields(generateFields("[].", BOOKMARK_RESPONSE_V2))
                                .build())));
    }

    @DisplayName("알림 설정 토글 : 즐겨찾기")
    @Test
    void toggleNotification() throws Exception {
        // given
        BookmarkResponseV2 response = createMockBookmarkResponseV2();

        given(bookmarkService.toggleNotification(any(), any())).willReturn(response);
        // when & then
        mockMvc.perform(
                        patch("/api/v2/notices/bookmarks/notifications")
                                .queryParam("source", NoticeSource.ACADEMIC_NOTICE.toString())
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("patch-v2-bookmark-toggleNotification",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Bookmark API V2")
                                .summary("즐겨찾기 알림 설정 토글")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .queryParameters(
                                        parameterWithName("source").description(generateLinkCode(NOTICE_SOURCE))
                                )
                                .responseFields(BOOKMARK_RESPONSE_V2)
                                .responseSchema(Schema.schema("BookmarkResponseV2"))
                                .build())));
    }
}