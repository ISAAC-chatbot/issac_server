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
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "device_token")
public class DeviceToken {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private boolean notificationConsent;

    public DeviceToken(Long userId, String token) {
        this.token = token;
        this.userId = userId;
        this.notificationConsent = true;
    }

    public void updateToken(String token) {
        this.token = token;
    }

    public void toggleNotificationConsent() {
        this.notificationConsent = !this.notificationConsent;
    }
}
