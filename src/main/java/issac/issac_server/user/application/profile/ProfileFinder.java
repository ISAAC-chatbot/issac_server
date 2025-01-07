package issac.issac_server.user.application.profile;

import issac.issac_server.user.application.UserFinder;
import issac.issac_server.user.domain.Profile;
import issac.issac_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileFinder {

    private final UserFinder userFinder;

    public Profile find(Long userId) {
        User user = userFinder.find(userId);
        return user.getProfile();
    }
}
