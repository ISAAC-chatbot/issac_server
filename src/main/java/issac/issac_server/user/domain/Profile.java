package issac.issac_server.user.domain;

import issac.issac_server.user.application.dto.ProfileUpdateRequest;
import issac.issac_server.user.application.dto.ProfileCreateRequest;
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
    @Enumerated(EnumType.STRING)
    private EducationLevel educationLevel;

    @Column
    private String collegeName;

    @Column
    private String department;

    @Column
    private String schoolEmail;

    @Column
    private String profilePhotoUrl;

    public Profile(ProfileCreateRequest request, String defaultProfilePhotoUrl) {
        this.nickname = request.getNickname();
        this.department = request.getDepartment();
        this.university = request.getUniversity();
        this.collegeName = request.getCollegeName();
        this.educationLevel = request.getEducationLevel();
        this.profilePhotoUrl = defaultProfilePhotoUrl;
    }

    public void update(ProfileUpdateRequest request) {
        this.nickname = request.getNickname() != null ? request.getNickname() : this.nickname;
        this.collegeName = request.getCollegeName() != null ? request.getCollegeName() : this.collegeName;
        this.department = request.getDepartment() != null ? request.getDepartment() : this.department;
        this.educationLevel = request.getEducationLevel() != null ? request.getEducationLevel() : this.educationLevel;
        this.profilePhotoUrl = request.getProfilePhotoUrl() != null ? request.getProfilePhotoUrl() : this.profilePhotoUrl;
        this.schoolEmail = request.getSchoolEmail() != null ? request.getSchoolEmail() : this.schoolEmail;
    }

    public void delete() {
        this.nickname = this.nickname + "(탈퇴)";
        this.profilePhotoUrl = "";
    }
}
