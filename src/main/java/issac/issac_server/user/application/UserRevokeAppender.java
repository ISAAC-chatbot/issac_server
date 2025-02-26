package issac.issac_server.user.application;

import issac.issac_server.user.domain.RevokeReasonType;
import issac.issac_server.user.domain.UserRevoke;
import issac.issac_server.user.domain.repository.UserRevokeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRevokeAppender{

    private final UserRevokeRepository userRevokeRepository;

    public void append(Long userId, RevokeReasonType type, String description){
        userRevokeRepository.save(UserRevoke.from(userId, type, description));
    }
}
