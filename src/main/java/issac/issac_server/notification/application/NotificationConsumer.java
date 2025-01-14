package issac.issac_server.notification.application;

import issac.issac_server.batch.application.dto.BookmarkQueueRequest;
import issac.issac_server.batch.application.dto.KeywordQueueRequest;
import issac.issac_server.device.application.DeviceTokenFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class NotificationConsumer {

    private final DeviceTokenFinder deviceTokenFinder;
    private final FCMSender fcmSender;
    private final NotificationAppender notificationAppender;

    @RabbitListener(queues = "keyword-queue", concurrency = "3-10")
    @Transactional
    public void receiveMessage(KeywordQueueRequest message) {

        Set<String> distinctTokens = deviceTokenFinder.findDistinctTokens(message.getUserIds());

        fcmSender.sendBulk(message.getRequest(), distinctTokens);

        notificationAppender.appendAll(message);
    }

    @RabbitListener(queues = "bookmark-queue", concurrency = "3-10")
    @Transactional
    public void receiveMessage(BookmarkQueueRequest message) {

        deviceTokenFinder.find(message.getUserId()).ifPresent(
                deviceToken -> fcmSender.send(message.getRequest(), deviceToken.getToken())
        );

        notificationAppender.append(message);
    }

}

