package issac.issac_server.user.application.profile;

import issac.issac_server.user.application.dto.ProfileCreateRequest;
import issac.issac_server.user.domain.User;
import issac.issac_server.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static issac.issac_server.common.config.Constant.DEFAULT_PHOTO;

@Component
@RequiredArgsConstructor
public class ProfileAppender {

    @Value("${issac.rds.domain}")
    private String domain;

    private final UserRepository userRepository;

    public void append(User user, ProfileCreateRequest request) {
        user.saveProfile(request, domain + DEFAULT_PHOTO);
    }
}
