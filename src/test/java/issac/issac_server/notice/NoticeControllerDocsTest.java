package issac.issac_server.notice;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import issac.issac_server.RestDocsSupport;
import issac.issac_server.notice.application.NoticeService;
import issac.issac_server.notice.application.dto.NoticePreviewResponse;
import issac.issac_server.notice.application.dto.NoticeResponse;
import issac.issac_server.notice.application.dto.NoticeSearchCondition;
import issac.issac_server.notice.presentation.NoticeController;
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
import static issac.issac_server.notice.constant.NoticeDocFields.*;
import static issac.issac_server.notice.constant.NoticeFactory.createMockNoticePreviewResponses;
import static issac.issac_server.notice.constant.NoticeFactory.createMockNoticeResponse;
import static issac.issac_server.notice.domain.NoticeSource.ACADEMIC_NOTICE;
import static issac.issac_server.reaction.domain.ReactionType.SCRAP;
import static issac.issac_server.user.domain.University.YONSEI;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class NoticeControllerDocsTest extends RestDocsSupport {

    private final NoticeService noticeService = mock(NoticeService.class);

    @Override
    protected Object initController() {
        return new NoticeController(noticeService);
    }

    @DisplayName("검색 : 공지사항")
    @Test
    void search() throws Exception {

        // given
        List<NoticePreviewResponse> responses = createMockNoticePreviewResponses();
        Pageable pageable = PageRequest.of(0, 10);
        Page<NoticePreviewResponse> pageResponses = new PageImpl<>(responses, pageable, responses.size());

        given(noticeService.search(any(NoticeSearchCondition.class), any(Pageable.class))).willReturn(pageResponses);

        // when & then
        mockMvc.perform(
                        get("/api/v1/notices")
                                .param("university", YONSEI.toString())
                                .param("source", ACADEMIC_NOTICE.toString())
                                .param("keyword", "나눔 사업")
                                .param("page", "0")
                                .param("size", "10")
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-v1-notice-search",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Notice API")
                                .summary("공지사항 검색")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .queryParameters(mergeParameters(
                                        new ParameterDescriptor[]{
                                                pageParam(),
                                                sizeParam(),
                                        },
                                        NOTICE_SEARCH_CONDITION
                                ))
                                // content 필드만 문서화
                                .responseFields(
                                        mergeFields(PAGE_RESPONSE, generateFields("content[].", NOTICE_PREVIEW_RESPONSE)))
                                .responseSchema(Schema.schema("NoticePreviewResponse"))
                                .build())));
    }

    @DisplayName("조회 : 공지사항")
    @Test
    void find() throws Exception {

        // given
        NoticeResponse response = createMockNoticeResponse();
        given(noticeService.find(any(), any())).willReturn(response);

        // when & then
        mockMvc.perform(
                        get("/api/v1/notices/{noticeId}", response.getId())
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-v1-notice-find",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Notice API")
                                .summary("공지사항 조회")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .pathParameters(
                                        parameterWithName("noticeId").description("공지사항 ID")
                                )
                                .responseFields(NOTICE_RESPONSE)
                                .responseSchema(Schema.schema("NoticeResponse"))
                                .build())));
    }

    @DisplayName("반응한 글 검색 : 공지사항")
    @Test
    void findNoticesByReaction() throws Exception {

        // given
        List<NoticePreviewResponse> responses = createMockNoticePreviewResponses();
        Pageable pageable = PageRequest.of(0, 10);
        Page<NoticePreviewResponse> pageResponses = new PageImpl<>(responses, pageable, responses.size());

        given(noticeService.findNoticesByReaction(any(), any(ReactionType.class), any(Pageable.class))).willReturn(pageResponses);

        // when & then
        mockMvc.perform(
                        get("/api/v1/notices/me/reactions")
                                .param("reactionType", SCRAP.toString())
                                .param("page", "0")
                                .param("size", "10")
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-v1-notice-findNoticesByReaction",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Notice API")
                                .summary("반응한 공지사항 검색")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .queryParameters(
                                        pageParam(),
                                        sizeParam(),
                                        parameterWithName("reactionType").description(generateLinkCode(REACTION_TYPE))
                                )
                                // content 필드만 문서화
                                .responseFields(
                                        mergeFields(PAGE_RESPONSE, generateFields("content[].", NOTICE_PREVIEW_RESPONSE)))
                                .responseSchema(Schema.schema("NoticePreviewResponse"))
                                .build())));
    }
}