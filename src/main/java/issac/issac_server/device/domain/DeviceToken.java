package issac.issac_server.device.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AllArgsConstructor
@Table(name = "device_token")
public class DeviceToken {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false)
    private String token;

    public void updateToken(String token) {
        this.token = token;
    }
}
