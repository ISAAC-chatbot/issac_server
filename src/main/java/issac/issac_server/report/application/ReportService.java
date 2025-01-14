package issac.issac_server.report.application;

import issac.issac_server.report.application.dto.ReportCreateRequest;
import issac.issac_server.report.application.dto.ReportPreviewResponse;
import issac.issac_server.report.application.dto.ReportResponse;
import issac.issac_server.report.application.event.ReportCreateEvent;
import issac.issac_server.report.domain.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportAppender reportAppender;
    private final ReportFinder reportFinder;
    private final ApplicationEventPublisher publisher;

    @Transactional
    public void save(Long userId, ReportCreateRequest request) {
        Report report = reportAppender.append(userId, request);
        publisher.publishEvent(new ReportCreateEvent(report));
    }

    public Page<ReportPreviewResponse> search(Pageable pageable) {
        return reportFinder.search(pageable);
    }

    public ReportResponse find(Long reportId) {
        return reportFinder.find(reportId);
    }
}
