package issac.issac_server.user.application.profile;

import issac.issac_server.file.application.S3Remover;
import issac.issac_server.user.application.dto.ProfileResponse;
import issac.issac_server.user.application.dto.ProfileUpdateRequest;
import issac.issac_server.user.domain.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static issac.issac_server.common.config.Constant.DEFAULT_PHOTO;

@Component
@RequiredArgsConstructor
public class ProfileUpdater {

    private final S3Remover s3Remover;

    public ProfileResponse update(Profile profile, ProfileUpdateRequest request) {
        if (request.getProfilePhotoUrl() != null && !profile.getProfilePhotoUrl().endsWith(DEFAULT_PHOTO)) {
            s3Remover.deleteObjectByUrl(profile.getProfilePhotoUrl());
        }
        profile.update(request);
        return ProfileResponse.from(profile);
    }
}
