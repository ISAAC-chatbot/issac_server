package issac.issac_server.batch.application;

import issac.issac_server.batch.application.dto.KeywordQueueRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KeywordJobWriter implements ItemWriter<KeywordQueueRequest> {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void write(Chunk<? extends KeywordQueueRequest> chunk) throws Exception {
        chunk.forEach(request -> rabbitTemplate.convertAndSend("notification-exchange", "keyword-routing-key", request));
    }
}

