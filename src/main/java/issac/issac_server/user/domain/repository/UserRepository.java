package issac.issac_server.user.domain.repository;

import issac.issac_server.auth.domain.OAuthProviderType;
import issac.issac_server.common.domain.EntityStatus;
import issac.issac_server.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByOauthInformationOauthProviderAndOauthInformationEmailAndEntityStatus(OAuthProviderType providerType, String email, EntityStatus status);
}
