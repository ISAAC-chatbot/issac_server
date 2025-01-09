package issac.issac_server.auth.application.oauth.kakao;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

@JsonNaming(SnakeCaseStrategy.class)
public class KaKaoRevokeRequest {
    private Long targetId;
    private String targetIdType;
}
