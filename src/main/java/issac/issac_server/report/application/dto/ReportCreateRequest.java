package issac.issac_server.report.application.dto;

import issac.issac_server.reaction.domain.TargetType;
import issac.issac_server.report.domain.ReportType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReportCreateRequest {

    @NotNull
    private TargetType targetType;

    @NotNull
    private String targetId;

    @NotNull
    private ReportType type;

    private String content;
}
