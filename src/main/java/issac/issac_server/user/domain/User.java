package issac.issac_server.user.domain;

import issac.issac_server.auth.application.dto.OAuthInfo;
import issac.issac_server.common.domain.BaseTimeEntity;
import issac.issac_server.common.domain.EntityStatus;
import issac.issac_server.user.application.dto.ProfileCreateRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(of = "id", callSuper = false)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private OAuthInformation oauthInformation;

    @Embedded
    private Profile profile;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    private boolean marketingConsent;

    @Enumerated(EnumType.STRING)
    private EntityStatus entityStatus;

    public void active() {
        this.entityStatus = EntityStatus.ACTIVE;
    }

    public void delete() {
        this.entityStatus = EntityStatus.DELETED;
    }

    public User(OAuthInfo oAuthInfo) {
        this.oauthInformation = new OAuthInformation(oAuthInfo);
        this.role = Role.UNREGISTERED_PROFILE;
    }

    public void saveProfile(ProfileCreateRequest request, String defaultProfilePhotoUrl) {
        this.profile = new Profile(request, defaultProfilePhotoUrl);
        this.role = Role.USER;
        this.marketingConsent = request.getMarketingConsent();
    }

    public void toggleMarketingConsent() {
        this.marketingConsent = !this.marketingConsent;
    }
}
