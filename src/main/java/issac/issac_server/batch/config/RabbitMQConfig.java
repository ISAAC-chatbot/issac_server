package issac.issac_server.batch.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.port}")
    private String port;

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Bean
    public ConnectionFactory connectionFactory() throws Exception {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setPort(Integer.parseInt(port));
        connectionFactory.setHost(host);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
//        connectionFactory.getRabbitConnectionFactory().useSslProtocol();
        return connectionFactory;
    }


    @Bean
    public RabbitTemplate rabbitTemplate() throws Exception {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory());
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public Queue keywordQueue() {
        return QueueBuilder.durable("keyword-queue")
                .withArgument("x-dead-letter-exchange", "dlx-exchange")  // Dead Letter Exchange 설정
                .withArgument("x-dead-letter-routing-key", "dlq-routing-key")
                .withArgument("x-message-ttl", 5000)                     // 메시지 TTL (5초)
                .build();
    }

    @Bean
    public Queue bookmarkQueue() {
        return QueueBuilder.durable("bookmark-queue")
                .withArgument("x-dead-letter-exchange", "dlx-exchange")  // Dead Letter Exchange 설정
                .withArgument("x-dead-letter-routing-key", "dlq-routing-key")
                .withArgument("x-message-ttl", 5000)                     // 메시지 TTL (5초)
                .build();
    }

    @Bean
    public Queue dlqQueue() {
        return new Queue("dlq-queue", true); // Dead Letter Queue
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("notification-exchange");
    }

    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange("dlx-exchange"); // Dead Letter Exchange
    }


    @Bean
    public Binding keywordBinding(Queue keywordQueue, DirectExchange exchange) {
        return BindingBuilder.bind(keywordQueue).to(exchange).with("keyword-routing-key");
    }

    @Bean
    public Binding bookmarkBinding(Queue bookmarkQueue, DirectExchange exchange) {
        return BindingBuilder.bind(bookmarkQueue).to(exchange).with("bookmark-routing-key");
    }
}
