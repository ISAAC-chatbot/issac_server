package issac.issac_server.user.domain;

import issac.issac_server.user.application.dto.ProfileUpdateRequest;
import issac.issac_server.user.application.dto.UserCreateRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Profile {

    @Column
    private String nickname;

    @Column
    @Enumerated(EnumType.STRING)
    private University university;

    @Column
    private String collegeName;

    @Column
    private String department;

    @Column
    @Enumerated(EnumType.STRING)
    private DegreeType degree;

    @Column
    private String schoolEmail;

    @Column
    private String profilePhotoUrl;

    public Profile(UserCreateRequest request, String defaultProfilePhotoUrl) {
        this.nickname = request.getNickname();
        this.department = request.getDepartment();
        this.university = request.getUniversity();
        this.collegeName = request.getCollegeName();
        this.degree = request.getDegree();
        this.schoolEmail = request.getSchoolEmail();
        this.profilePhotoUrl = defaultProfilePhotoUrl;
    }

    public void update(ProfileUpdateRequest request) {
        this.nickname = request.getNickname();
        this.collegeName = request.getCollegeName();
        this.department = request.getDepartment();
        this.degree = request.getDegree();
        this.profilePhotoUrl = request.getProfilePhotoUrl();
    }

    public void delete() {
        this.nickname = this.nickname + "(탈퇴)";
        this.profilePhotoUrl = "";
    }
}
