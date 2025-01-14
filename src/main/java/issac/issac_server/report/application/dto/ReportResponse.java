package issac.issac_server.report.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import issac.issac_server.post.application.dto.response.UserInfoResponse;
import issac.issac_server.reaction.domain.TargetType;
import issac.issac_server.report.domain.Report;
import issac.issac_server.report.domain.ReportType;
import issac.issac_server.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

import static issac.issac_server.common.config.Constant.FORMAT_LOCAL_DATE_TIME;

@Getter
@AllArgsConstructor
public class ReportResponse {

    private ReportType type;

    private TargetType targetType;

    private String targetId;

    private String content;

    private UserInfoResponse author;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FORMAT_LOCAL_DATE_TIME)
    private LocalDateTime createdAt;

    public static ReportResponse from(Report report, User author){
        return new ReportResponse(
                report.getType(),
                report.getTargetType(),
                report.getTargetId(),
                report.getContent(),
                UserInfoResponse.from(author),
                report.getCreatedDateTime()
        );
    }
}
