package issac.issac_server.auth.application.oauth.kakao;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import issac.issac_server.auth.application.dto.OAuthInfo;
import issac.issac_server.auth.domain.OAuthProviderType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonNaming(SnakeCaseStrategy.class)
public class KakaoInfoResponse {

    private String id;
    private boolean hasSignedUp;
    private LocalDateTime connectedAt;
    private KakaoAccount kakaoAccount;

    @Data
    @JsonNaming(SnakeCaseStrategy.class)
    public static class KakaoAccount {
        private boolean profileNeedsAgreement;
        private boolean profileNicknameNeedsAgreement;
        private boolean profileImageNeedsAgreement;
        private Profile profile;
        private boolean nameNeedsAgreement;
        private String name;
        private boolean emailNeedsAgreement;
        private boolean isEmailValid;
        private boolean isEmailVerified;
        private String email;
        private boolean ageRangeNeedsAgreement;
        private String ageRange;
        private boolean birthYearNeedsAgreement;
        private String birthYear;
        private boolean birthdayNeedsAgreement;
        private String birthday;
        private String birthdayType;
        private boolean genderNeedsAgreement;
        private String gender;
        private boolean phoneNumberNeedsAgreement;
        private String phoneNumber;
        private boolean ciNeedsAgreement;
        private String ci;
        private LocalDateTime ciAuthenticatedAt;
    }

    @Data
    @JsonNaming(SnakeCaseStrategy.class)
    public static class Profile {
        private String nickname;
        private String thumbnailImageUrl;
        private String profileImageUrl;
        private boolean isDefaultImage;
    }

    public OAuthInfo toOauthInfo() {
        return new OAuthInfo(id, kakaoAccount.email, OAuthProviderType.KAKAO);
    }
}
