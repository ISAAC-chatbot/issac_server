package issac.issac_server.user.application;

import issac.issac_server.auth.application.dto.OAuthInfo;
import issac.issac_server.user.domain.User;
import issac.issac_server.user.domain.repository.UserRepository;
import issac.issac_server.user.exception.UserErrorCode;
import issac.issac_server.user.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFinder {

    private final UserRepository userRepository;
    private final UserAppender userAppender;

    public User find(OAuthInfo oAuthInfo) {
        return userRepository.findByOauthInformationOauthProviderAndOauthInformationEmail(oAuthInfo.getProvider(), oAuthInfo.getEmail())
                .orElse(userAppender.append(oAuthInfo));
    }

    public User find(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_EXIST));
    }
}
