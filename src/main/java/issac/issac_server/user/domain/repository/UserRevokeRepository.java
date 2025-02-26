package issac.issac_server.user.domain.repository;

import issac.issac_server.user.domain.UserRevoke;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRevokeRepository extends JpaRepository<UserRevoke, Long> {
}
