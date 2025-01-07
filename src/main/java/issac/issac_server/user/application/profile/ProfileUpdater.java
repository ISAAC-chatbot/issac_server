package issac.issac_server.user.application.profile;

import issac.issac_server.file.application.S3Remover;
import issac.issac_server.user.application.dto.ProfileResponse;
import issac.issac_server.user.application.dto.ProfileUpdateRequest;
import issac.issac_server.user.domain.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileUpdater {

    private final S3Remover s3Remover;

    public ProfileResponse update(Profile profile, ProfileUpdateRequest request) {
        s3Remover.deleteObjectByUrl(profile.getProfilePhotoUrl());
        profile.update(request);
        return ProfileResponse.from(profile);
    }
}
