package issac.issac_server.batch.application;

import issac.issac_server.batch.application.dto.BookmarkQueueRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookmarkJobWriter implements ItemWriter<BookmarkQueueRequest> {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void write(Chunk<? extends BookmarkQueueRequest> chunk) throws Exception {
        chunk.forEach(request -> rabbitTemplate.convertAndSend("notification-exchange", "bookmark-routing-key", request));
    }
}

