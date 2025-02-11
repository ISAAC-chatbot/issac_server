package issac.issac_server.auth.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtDecoder {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Map<String, Object> decodeIdToken(String idToken) {
        try {
            // JWT는 "header.payload.signature" 형식 -> payload 부분 추출
            String[] parts = idToken.split("\\.");
            if (parts.length < 2) {
                throw new IllegalArgumentException("Invalid ID Token format");
            }

            // Base64 디코딩 (URL Safe)
            String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]));

            // JSON을 Map으로 변환
            return objectMapper.readValue(payloadJson, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to decode ID Token", e);
        }
    }
}
