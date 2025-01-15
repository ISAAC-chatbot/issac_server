package issac.issac_server.report.constant;

import issac.issac_server.reaction.domain.TargetType;
import issac.issac_server.report.application.dto.ReportCreateRequest;
import issac.issac_server.report.application.dto.ReportPreviewResponse;
import issac.issac_server.report.application.dto.ReportResponse;
import issac.issac_server.report.domain.ReportType;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static issac.issac_server.post.constant.PostFactory.createMockUserInfoResponse;

public class ReportFactory {

    public static ReportCreateRequest createMockReportCreateRequest() {
        return new ReportCreateRequest(
                TargetType.POST,
                "1",
                ReportType.SPAM,
                "스팸 글입니다"
        );
    }

    public static ReportPreviewResponse createMockReportPreviewResponse() {
        return new ReportPreviewResponse(
                ReportType.SPAM,
                TargetType.POST,
                createMockUserInfoResponse()
        );
    }

    public static List<ReportPreviewResponse> createMockReportPreviewResponses() {
        return Arrays.asList(createMockReportPreviewResponse(), createMockReportPreviewResponse());
    }

    public static ReportResponse createMockReportResponse() {
        return new ReportResponse(
                ReportType.SPAM,
                TargetType.POST,
                "1",
                "스팸 글 입니다",
                createMockUserInfoResponse(),
                LocalDateTime.now()
        );
    }
}
