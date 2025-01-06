package issac.issac_server.notice;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import issac.issac_server.RestDocsSupport;
import issac.issac_server.notice.application.NoticeService;
import issac.issac_server.notice.application.dto.NoticePreviewResponse;
import issac.issac_server.notice.application.dto.NoticeSearchCondition;
import issac.issac_server.notice.presentation.NoticeController;
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
import static issac.issac_server.notice.constant.NoticeDocFields.NOTICE_PREVIEW_RESPONSE;
import static issac.issac_server.notice.constant.NoticeDocFields.NOTICE_SEARCH_CONDITION;
import static issac.issac_server.notice.constant.NoticeFactory.createMockNoticePreviewResponses;
import static issac.issac_server.notice.domain.NoticeSource.ACADEMIC_NOTICE;
import static issac.issac_server.user.domain.University.YONSEI;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
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
}