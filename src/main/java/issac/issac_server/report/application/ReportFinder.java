package issac.issac_server.report.application;

import issac.issac_server.report.application.dto.ReportPreviewResponse;
import issac.issac_server.report.application.dto.ReportResponse;
import issac.issac_server.report.domain.Report;
import issac.issac_server.report.domain.ReportRepository;
import issac.issac_server.report.exception.ReportErrorCode;
import issac.issac_server.report.exception.ReportException;
import issac.issac_server.user.application.UserFinder;
import issac.issac_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportFinder {

    private final ReportRepository reportRepository;
    private final UserFinder userFinder;

    public Page<ReportPreviewResponse> search(Pageable pageable) {
        return reportRepository.findAll(pageable).map(report -> ReportPreviewResponse.from(report, userFinder.find(report.getUserId())));
    }

    public ReportResponse find(Long reportId) {
        Report report = reportRepository.findById(reportId).orElseThrow(()-> new ReportException(ReportErrorCode.NOT_FOUND));
        User author = userFinder.find(report.getUserId());
        return ReportResponse.from(report, author);
    }
}
