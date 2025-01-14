package issac.issac_server.report.application.event;

import issac.issac_server.device.application.DeviceTokenFinder;
import issac.issac_server.notification.application.FCMSender;
import issac.issac_server.notification.application.dto.NotificationRequest;
import issac.issac_server.user.application.UserFinder;
import issac.issac_server.user.domain.Role;
import issac.issac_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ReportEventListener {

    private final FCMSender fcmSender;
    private final UserFinder userFinder;
    private final DeviceTokenFinder deviceTokenFinder;

    @Async
    @EventListener
    public void notifyToAdmins(ReportCreateEvent event) {

        List<User> users = userFinder.findAllByRole(Role.ADMIN);
        Set<String> distinctTokens = deviceTokenFinder.findDistinctTokens(users.stream().map(User::getId).toList());

        User author = userFinder.find(event.getReport().getUserId());
        NotificationRequest request = NotificationRequest.from(event.getReport(), author.getProfile().getNickname());

        fcmSender.send(request, distinctTokens);
    }

}
