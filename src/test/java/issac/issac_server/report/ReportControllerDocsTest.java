package issac.issac_server.report;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import issac.issac_server.RestDocsSupport;
import issac.issac_server.report.application.ReportService;
import issac.issac_server.report.application.dto.ReportCreateRequest;
import issac.issac_server.report.application.dto.ReportPreviewResponse;
import issac.issac_server.report.application.dto.ReportResponse;
import issac.issac_server.report.presentation.ReportController;
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
import static issac.issac_server.document.utils.ApiDocumentUtils.*;
import static issac.issac_server.document.utils.DocumentFormatGenerator.generateFields;
import static issac.issac_server.document.utils.DocumentFormatGenerator.mergeFields;
import static issac.issac_server.report.constant.ReportDocFields.*;
import static issac.issac_server.report.constant.ReportFactory.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ReportControllerDocsTest extends RestDocsSupport {

    private final ReportService reportService = mock(ReportService.class);

    @Override
    protected Object initController() {
        return new ReportController(reportService);
    }

    @DisplayName("접수 : 신고")
    @Test
    void save() throws Exception {
        // given
        ReportCreateRequest request = createMockReportCreateRequest();

        // when & then
        mockMvc.perform(
                        post("/api/v1/reports")
                                .content(objectMapper.writeValueAsString(request))
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("post-v1-report-save",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Report API")
                                .summary("신고 접수")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .requestFields(REPORT_CREATE_REQUEST)
                                .requestSchema(Schema.schema("ReportCreateRequest"))
                                .build())));
    }

    @DisplayName("검색 : 신고")
    @Test
    void search() throws Exception {

        // given
        List<ReportPreviewResponse> responses = createMockReportPreviewResponses();
        Pageable pageable = PageRequest.of(0, 10);
        Page<ReportPreviewResponse> pageResponses = new PageImpl<>(responses, pageable, responses.size());

        given(reportService.search(any(Pageable.class))).willReturn(pageResponses);

        // when & then
        mockMvc.perform(
                        get("/api/v1/reports")
                                .param("page", "0")
                                .param("size", "10")
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-v1-report-search",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Report API")
                                .summary("신고 검색(관리자용)")
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
                                        generateFields("content[].", REPORT_PREVIEW_RESPONSE)
                                ))
                                .responseSchema(Schema.schema("ReportPreviewResponse"))
                                .build())));
    }

    @DisplayName("조회 : 신고")
    @Test
    void find() throws Exception {

        // given
        ReportResponse response = createMockReportResponse();

        given(reportService.find(any())).willReturn(response);

        // when & then
        mockMvc.perform(
                        get("/api/v1/reports/{reportId}", 1L)
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-v1-report-find",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Report API")
                                .summary("신고 글 조회(관리자용)")
                                .requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer 토큰 (예: `Bearer {ACCESS_TOKEN}`)")
                                )
                                .responseFields(REPORT_RESPONSE)
                                .responseSchema(Schema.schema("ReportResponse"))
                                .build())));
    }
}