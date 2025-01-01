package issac.issac_server.user.application;

import issac.issac_server.auth.application.dto.OAuthInfo;
import issac.issac_server.user.application.dto.UserCreateRequest;
import issac.issac_server.user.domain.User;
import issac.issac_server.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAppender {

    private final UserRepository userRepository;

    public void signup(User user, UserCreateRequest request) {
        user.signup(request);
    }

    public User append(OAuthInfo oAuthInfo) {
        User user = new User(oAuthInfo);
        user.active();
        return userRepository.save(user);
    }
}
