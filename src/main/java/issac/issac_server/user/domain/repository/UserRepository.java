package issac.issac_server.user.domain.repository;

import issac.issac_server.auth.domain.OAuthProviderType;
import issac.issac_server.common.domain.EntityStatus;
import issac.issac_server.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByOauthInformationOauthProviderAndOauthInformationOauthIdAndEntityStatus(OAuthProviderType providerType, String oauthId, EntityStatus status);

    Optional<User> findByIdAndEntityStatus(Long id, EntityStatus status);
}
