package issac.issac_server.user.domain;

import issac.issac_server.auth.application.dto.OAuthInfo;
import issac.issac_server.common.domain.BaseCreateTimeEntity;
import issac.issac_server.common.domain.EntityStatus;
import issac.issac_server.user.application.dto.UserCreateRequest;
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
public class User extends BaseCreateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private OAuthInformation oauthInformation;
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
    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private EntityStatus entityStatus;

    public void active() {
        entityStatus = EntityStatus.ACTIVE;
    }

    public void delete() {
        entityStatus = EntityStatus.DELETED;
    }

    public User(OAuthInfo oAuthInfo) {
        this.oauthInformation = new OAuthInformation(oAuthInfo);
        this.role = Role.UNREGISTERED_PROFILE;
    }

    public void signup(UserCreateRequest request) {
        this.nickname = request.getNickname();
        this.department = request.getDepartment();
        this.university = request.getUniversity();
        this.collegeName = request.getCollegeName();
        this.degree = request.getDegree();
        this.role = Role.STUDENT;
    }
}
