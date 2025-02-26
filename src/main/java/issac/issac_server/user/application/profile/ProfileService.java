package issac.issac_server.user.application.profile;

import issac.issac_server.user.application.UserFinder;
import issac.issac_server.user.application.dto.ProfileCreateRequest;
import issac.issac_server.user.application.dto.ProfileResponse;
import issac.issac_server.user.application.dto.ProfileUpdateRequest;
import issac.issac_server.user.application.dto.UserResponse;
import issac.issac_server.user.domain.Profile;
import issac.issac_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserFinder userFinder;
    private final ProfileFinder profileFinder;
    private final ProfileUpdater profileUpdater;
    private final ProfileAppender profileAppender;

    public ProfileResponse findProfile(Long userId) {
        return ProfileResponse.from(profileFinder.find(userId));
    }

    @Transactional
    public ProfileResponse updateMyProfile(Long userId, ProfileUpdateRequest request) {
        Profile profile = profileFinder.find(userId);
        return profileUpdater.update(profile, request);
    }

    @Transactional
    public UserResponse saveProfile(Long userId, ProfileCreateRequest request) {
        User user = userFinder.find(userId);
        profileAppender.append(user, request);
        return UserResponse.from(user);
    }

}
