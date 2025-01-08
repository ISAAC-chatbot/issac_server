package issac.issac_server.post.application.dto.response;

import issac.issac_server.user.domain.Role;
import issac.issac_server.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoResponse {

    private Long userId;
    private String nickname;
    private String department;
    private String profilePhotoUrl;
    private Role role;

    public static UserInfoResponse from(User user) {
        return new UserInfoResponse(
                user.getId(),
                user.getProfile().getNickname(),
                user.getProfile().getDepartment(),
                user.getProfile().getProfilePhotoUrl(),
                user.getRole()
        );
    }
}
