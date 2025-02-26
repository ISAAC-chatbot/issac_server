package issac.issac_server.auth.application;

import issac.issac_server.auth.application.dto.*;
import issac.issac_server.auth.domain.RefreshToken;
import issac.issac_server.auth.exception.AuthException;
import issac.issac_server.auth.infrastructure.JwtTokenProvider;
import issac.issac_server.file.application.S3Remover;
import issac.issac_server.user.application.UserFinder;
import issac.issac_server.user.domain.Role;
import issac.issac_server.user.domain.User;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static issac.issac_server.auth.exception.AuthErrorCode.INVALID_EMAIL_DOMAIN;
import static issac.issac_server.common.config.Constant.DEFAULT_PHOTO;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserFinder userFinder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenFinder refreshTokenFinder;
    private final EmailSender emailSender;
    private final S3Remover s3Remover;

    public LoginResponse login(OAuthInfo oAuthInfo) {
        User user = userFinder.find(oAuthInfo);
        return generateToken(user);
    }

    @Transactional(readOnly = true)
    public LoginResponse guestLogin() {
        String accessToken = jwtTokenProvider.generateGuestAccessToken();
        return new LoginResponse(accessToken, Role.GUEST);
    }

    public LoginResponse refresh(RefreshTokenRequest request) {
        jwtTokenProvider.validateRefreshToken(request.getToken());
        RefreshToken refreshToken = refreshTokenFinder.find(request.getToken());
        User user = userFinder.find(refreshToken.getUserId());
        return generateToken(user);
    }

    private LoginResponse generateToken(User user) {
        String accessToken = jwtTokenProvider.generateAccessToken(user);
        String refreshToken = jwtTokenProvider.generateRefreshToken(user);
        return new LoginResponse(accessToken, refreshToken, user.getRole());
    }

    public EmailResponse sendEmailVerification(EmailRequest request) throws MessagingException {
        validateEmailDomain(request);
        return emailSender.send(request);
    }

    private void validateEmailDomain(EmailRequest request) {
        if (!request.getEmail().endsWith(request.getUniversity().getDomain())) {
            throw new AuthException(INVALID_EMAIL_DOMAIN);
        }
    }

    public void revoke(User user) {
        user.delete();
        if (!user.getProfile().getProfilePhotoUrl().endsWith(DEFAULT_PHOTO)) {
            s3Remover.deleteObjectByUrl(user.getProfile().getProfilePhotoUrl());
        }
        user.getProfile().delete();
    }
}
