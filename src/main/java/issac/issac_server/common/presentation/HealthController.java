package issac.issac_server.common.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/health")
@RestController
public class HealthController {

    @GetMapping
    public String profile() {
        return "health check";
    }

    @GetMapping("/logs")
    public String getLogs() throws IOException {
        // 로그 파일 경로
        String logFilePath = "application.log";

        // 로그 파일 읽기
        List<String> lines = Files.readAllLines(Paths.get(logFilePath));
        return String.join("\n", lines);
    }
}
