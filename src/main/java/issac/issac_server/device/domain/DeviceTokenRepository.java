package issac.issac_server.device.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface DeviceTokenRepository extends JpaRepository<DeviceToken, Long> {

    @Query("SELECT DISTINCT d.token FROM DeviceToken d WHERE d.userId IN :userIds")
    Set<String> findDistinctTokensByUserIdIn(List<Long> userIds);
}
