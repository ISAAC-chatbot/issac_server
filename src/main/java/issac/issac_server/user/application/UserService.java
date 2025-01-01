package issac.issac_server.user.application;

import issac.issac_server.user.application.dto.UserCreateRequest;
import issac.issac_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserFinder userFinder;
    private final UserAppender userAppender;

    @Transactional
    public void signup(Long userId, UserCreateRequest request) {
        User user = userFinder.find(userId);
        userAppender.signup(user, request);
    }
}
