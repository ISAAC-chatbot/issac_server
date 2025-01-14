package issac.issac_server.batch.application;

import issac.issac_server.batch.application.dto.RabbitMQResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQKeywordWriter implements ItemWriter<RabbitMQResponse> {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void write(Chunk<? extends RabbitMQResponse> chunk) throws Exception {
        rabbitTemplate.convertAndSend("notification-exchange", "keyword-routing-key", chunk);
    }
}

