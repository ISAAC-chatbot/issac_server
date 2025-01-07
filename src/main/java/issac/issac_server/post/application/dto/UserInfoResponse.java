package issac.issac_server.post.application.dto;

import issac.issac_server.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoResponse {

    private Long userId;
    private String nickname;
    private String department;

    public static UserInfoResponse from(User user) {
        return new UserInfoResponse(
                user.getId(),
                user.getProfile().getNickname(),
                user.getProfile().getDepartment()
        );
    }
}
