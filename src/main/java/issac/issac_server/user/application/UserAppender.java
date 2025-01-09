package issac.issac_server.user.application;

import issac.issac_server.auth.application.dto.OAuthInfo;
import issac.issac_server.user.application.dto.UserCreateRequest;
import issac.issac_server.user.domain.User;
import issac.issac_server.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static issac.issac_server.common.config.Constant.DEFAULT_PHOTO;

@Component
@RequiredArgsConstructor
public class UserAppender {

    @Value("${issac.rds.domain}")
    private String domain;

    private final UserRepository userRepository;

    public void signup(User user, UserCreateRequest request) {
        user.signup(request, domain + DEFAULT_PHOTO);
    }

    public User append(OAuthInfo oAuthInfo) {
        User user = new User(oAuthInfo);
        user.active();
        return userRepository.save(user);
    }
}
