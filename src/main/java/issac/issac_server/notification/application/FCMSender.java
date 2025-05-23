package issac.issac_server.notification.application;

import com.google.firebase.messaging.*;
import issac.issac_server.notification.application.dto.NotificationCreateRequest;
import issac.issac_server.notification.application.dto.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class FCMSender {

    private final FirebaseMessaging firebaseMessaging;

    private static final int MAX_TOKENS_PER_BATCH = 800;

    @Async
    public void sendBulk(NotificationRequest request, Set<String> deviceTokens) {

        List<List<String>> tokenBatches = splitIntoBatches(new ArrayList<>(deviceTokens), MAX_TOKENS_PER_BATCH);

        for (List<String> batch : tokenBatches) {
            MulticastMessage message = MulticastMessage.builder()
                    .setNotification(Notification.builder()
                            .setTitle(request.getFcmTitle())
                            .setBody(request.getContent())
                            .build()
                    )
                    .putData("notificationType", request.getNotificationType().toString())
                    .putData("title", request.getTitle())
                    .putData("content", request.getContent())
                    .putData("entityType", request.getEntityType().toString())
                    .putData("entityId", request.getEntityId())
                    .putData("author", request.getAuthor())
                    .putData("fcmTitle", request.getFcmTitle())
                    .addAllTokens(batch)
                    .build();

            try {
                BatchResponse response = firebaseMessaging.sendEachForMulticast(message);

                log.info("FCM 전송: Batch sent successfully: {} successful, {} failed",
                        response.getSuccessCount(), response.getFailureCount());

            } catch (FirebaseMessagingException e) {
                log.error("Error sending FCM messages", e);
            }
        }
    }

    @Async
    public void sendBulk(NotificationCreateRequest request) {

        List<List<String>> tokenBatches = splitIntoBatches(new ArrayList<>(request.getDeviceTokens()), MAX_TOKENS_PER_BATCH);

        for (List<String> batch : tokenBatches) {
            MulticastMessage message = MulticastMessage.builder()
                    .setNotification(Notification.builder()
                            .setTitle(request.getTitle())
                            .setBody(request.getContent())
                            .build()
                    )
                    .addAllTokens(batch)
                    .build();

            try {
                BatchResponse response = firebaseMessaging.sendEachForMulticast(message);

                log.info("SELF FCM 전송: Batch sent successfully: {} successful, {} failed",
                        response.getSuccessCount(), response.getFailureCount());

            } catch (FirebaseMessagingException e) {
                log.error("Error sending FCM messages", e);
            }
        }
    }

    @Async
    public void send(NotificationRequest request, Set<String> deviceTokens) {

        MulticastMessage message = MulticastMessage.builder()
                .setNotification(Notification.builder()
                        .setTitle(request.getFcmTitle())
                        .setBody(request.getContent())
                        .build()
                )
                .putData("notificationType", request.getNotificationType().toString())
                .putData("title", request.getTitle())
                .putData("content", request.getContent())
                .putData("entityType", request.getEntityType().toString())
                .putData("entityId", request.getEntityId())
                .putData("author", request.getAuthor())
                .putData("fcmTitle", request.getFcmTitle())
                .addAllTokens(deviceTokens)
                .build();

        try {
            BatchResponse response = firebaseMessaging.sendEachForMulticast(message);

            log.info("FCM 전송: Batch sent successfully: {} successful, {} failed",
                    response.getSuccessCount(), response.getFailureCount());

        } catch (FirebaseMessagingException e) {
            log.error("Error sending FCM messages", e);
        }
    }
    @Async
    public void send(NotificationRequest request, String deviceToken) {
        com.google.firebase.messaging.Message message = com.google.firebase.messaging.Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(request.getFcmTitle())
                        .setBody(request.getContent())
                        .build()
                )
                .putData("notificationType", request.getNotificationType().toString())
                .putData("title", request.getTitle())
                .putData("content", request.getContent())
                .putData("entityType", request.getEntityType().toString())
                .putData("entityId", request.getEntityId())
                .putData("author", request.getAuthor())
                .putData("fcmTitle", request.getFcmTitle())
                .setToken(deviceToken)
                .build();

        try {
            String response = firebaseMessaging.send(message);
            log.info("Single message sent successfully: {}", response);
        } catch (FirebaseMessagingException e) {
            log.warn("Error sending single FCM message");
        }
    }

    private List<List<String>> splitIntoBatches(List<String> tokens, int batchSize) {
        List<List<String>> batches = new ArrayList<>();

        for (int i = 0; i < tokens.size(); i += batchSize) {
            int end = Math.min(i + batchSize, tokens.size());
            batches.add(new ArrayList<>(tokens.subList(i, end)));
        }
        return batches;
    }
}

