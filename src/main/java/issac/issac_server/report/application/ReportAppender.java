package issac.issac_server.report.application;

import issac.issac_server.report.application.dto.ReportCreateRequest;
import issac.issac_server.report.domain.Report;
import issac.issac_server.report.domain.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportAppender {

    private final ReportRepository reportRepository;

    public Report append(Long userId, ReportCreateRequest request) {
        Report report = Report.of(userId, request);
        report.active();
        reportRepository.save(report);
        return report;
    }
}
