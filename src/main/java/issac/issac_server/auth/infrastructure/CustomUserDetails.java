package issac.issac_server.auth.infrastructure;

import issac.issac_server.common.domain.EntityStatus;
import issac.issac_server.user.domain.User;
import issac.issac_server.user.domain.repository.UserRepository;
import issac.issac_server.user.exception.UserErrorCode;
import issac.issac_server.user.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByIdAndEntityStatus(Long.valueOf(username), EntityStatus.ACTIVE)
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_EXIST));

        return new UserPrincipal(user);
    }
}
