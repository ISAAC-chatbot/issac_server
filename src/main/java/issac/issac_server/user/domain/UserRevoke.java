package issac.issac_server.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user_revoke")
public class UserRevoke {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false)
    private String reason;

    public static UserRevoke from(Long userId, RevokeReasonType type, String reasonDescription) {
        return new UserRevoke(userId, StringUtils.hasText(reasonDescription) ? reasonDescription : type.getDescription());
    }
}
