package issac.issac_server.user.application;

import issac.issac_server.auth.application.dto.OAuthInfo;
import issac.issac_server.common.domain.EntityStatus;
import issac.issac_server.user.domain.Role;
import issac.issac_server.user.domain.User;
import issac.issac_server.user.domain.repository.UserRepository;
import issac.issac_server.user.exception.UserErrorCode;
import issac.issac_server.user.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class UserFinder {

    private final UserRepository userRepository;
    private final UserAppender userAppender;

    public User find(OAuthInfo oAuthInfo) {
        return userRepository.findByOauthInformationOauthProviderAndOauthInformationOauthIdAndEntityStatus(oAuthInfo.getProvider(), oAuthInfo.getId(), EntityStatus.ACTIVE)
                .orElseGet(() -> userAppender.append(oAuthInfo));
    }

    public User find(Long userId) {
        return userRepository.findByIdAndEntityStatus(userId, EntityStatus.ACTIVE)
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_EXIST));
    }

    public List<User> findAllByRole(Role role) {
        return userRepository.findAllByRole(role);
    }
}
