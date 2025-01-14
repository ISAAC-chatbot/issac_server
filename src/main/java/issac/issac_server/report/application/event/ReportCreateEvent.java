package issac.issac_server.report.application.event;

import issac.issac_server.report.domain.Report;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReportCreateEvent {
    private Report report;
}
