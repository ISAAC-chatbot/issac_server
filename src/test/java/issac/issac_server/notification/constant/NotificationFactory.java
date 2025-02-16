package issac.issac_server.notification.constant;

import issac.issac_server.notification.application.dto.NotificationResponse;
import issac.issac_server.notification.application.dto.NotificationUpdateRequest;
import issac.issac_server.notification.domain.NotificationType;
import issac.issac_server.reaction.domain.TargetType;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class NotificationFactory {

    public static NotificationResponse createMockNotificationResponse() {
        return new NotificationResponse(
                1L,
                NotificationType.KEYWORD,
                "장학금",
                "[국제언어교육원] 2024년 여름 학기 유학생활도우미 봉사장학생 모집",
                TargetType.NOTICE,
                "MIwfY5QBvxJ7OZdJ9CS3",
                "장학팀",
                LocalDateTime.now(),
                false
        );
    }

    public static List<NotificationResponse> createMockNotificationResponses() {
        return Arrays.asList(createMockNotificationResponse(), createMockNotificationResponse());
    }

    public static NotificationUpdateRequest createMockNotificationUpdateRequest() {
        return new NotificationUpdateRequest(
                NotificationType.KEYWORD,
                TargetType.NOTICE,
                "MIwfY5QBvxJ7OZdJ9CS3"
        );
    }
}
