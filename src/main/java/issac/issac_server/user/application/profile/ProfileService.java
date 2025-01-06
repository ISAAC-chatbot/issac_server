package issac.issac_server.user.application.profile;

import issac.issac_server.user.application.dto.ProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileFinder profileFinder;

    public ProfileResponse findProfile(Long userId) {
        return profileFinder.find(userId);
    }
}
