package issac.issac_server.user.application.profile;

import issac.issac_server.user.application.dto.ProfileResponse;
import issac.issac_server.user.application.dto.ProfileUpdateRequest;
import issac.issac_server.user.domain.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileFinder profileFinder;
    private final ProfileUpdater profileUpdater;

    public ProfileResponse findProfile(Long userId) {
        return ProfileResponse.from(profileFinder.find(userId));
    }

    @Transactional
    public ProfileResponse updateMyProfile(Long userId, ProfileUpdateRequest request) {
        Profile profile = profileFinder.find(userId);
        return profileUpdater.update(profile, request);
    }
}
