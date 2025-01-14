package issac.issac_server.notification.application;

import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.MulticastMessage;
import issac.issac_server.notification.application.dto.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class FCMSender {

    private final FirebaseMessaging firebaseMessaging;

    private static final int MAX_TOKENS_PER_BATCH = 400;

    public void sendBulk(NotificationRequest request, Set<String> deviceTokens) {

        List<List<String>> tokenBatches = splitIntoBatches(new ArrayList<>(deviceTokens), MAX_TOKENS_PER_BATCH);

        for (List<String> batch : tokenBatches) {
            MulticastMessage message = MulticastMessage.builder()
                    .putData("notificationType",request.getNotificationType().toString())
                    .putData("title", request.getTitle())
                    .putData("content", request.getContent())
                    .addAllTokens(batch)
                    .build();

            try {
                BatchResponse response = firebaseMessaging.sendMulticast(message);

                log.info("Batch sent successfully: {} successful, {} failed",
                        response.getSuccessCount(), response.getFailureCount());

//                response.getResponses().forEach(r -> {
//                    if (!r.isSuccessful()) {
//                        log.error("Failed to send message: {}", r.getException().getMessage());
//                    }
//                });

            } catch (FirebaseMessagingException e) {
                log.error("Error sending FCM messages", e);
            }
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

