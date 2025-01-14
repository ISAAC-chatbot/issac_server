package issac.issac_server.report.application.dto;

import issac.issac_server.post.application.dto.response.UserInfoResponse;
import issac.issac_server.reaction.domain.TargetType;
import issac.issac_server.report.domain.Report;
import issac.issac_server.report.domain.ReportType;
import issac.issac_server.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReportPreviewResponse {

    private ReportType type;

    private TargetType targetType;

    private UserInfoResponse author;

    public static ReportPreviewResponse from(Report report, User author) {
        return new ReportPreviewResponse(report.getType(), report.getTargetType(), UserInfoResponse.from(author));
    }
}
