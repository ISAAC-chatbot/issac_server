package issac.issac_server.report.presentation;

import issac.issac_server.auth.config.auth.Auth;
import issac.issac_server.report.application.ReportService;
import issac.issac_server.report.application.dto.ReportCreateRequest;
import issac.issac_server.report.application.dto.ReportPreviewResponse;
import issac.issac_server.report.application.dto.ReportResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @PostMapping
    public ResponseEntity<Void> save(
            @Auth Long userId,
            @RequestBody @Valid ReportCreateRequest request
    ) {
        reportService.save(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Page<ReportPreviewResponse>> search(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.search(pageable));
    }

    @GetMapping("/{reportId}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<ReportResponse> find(@PathVariable Long reportId) {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.find(reportId));
    }
}
