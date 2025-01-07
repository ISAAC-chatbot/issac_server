package issac.issac_server.file.presentation;

import issac.issac_server.file.application.FileService;
import issac.issac_server.file.application.dto.PreSignedUrlResponse;
import issac.issac_server.file.domain.FileTargetType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/file")
@Secured({"ROLE_USER", "ROLE_STUDENT", "ROLE_TEACHING_ASSISTANT", "ROLE_PROFESSOR", "ROLE_ADMIN"})
public class FileController {

    private final FileService fileService;

    @GetMapping("/presigned-url")
    public ResponseEntity<PreSignedUrlResponse> getPresignedUrl(@RequestParam FileTargetType targetType) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fileService.getPresignedUrl(targetType));
    }
}
