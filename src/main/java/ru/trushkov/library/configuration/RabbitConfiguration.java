package ru.trushkov.library.configuration;

import lombok.Setter;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
public class RabbitConfiguration {

    @Value("${queue.create.author.name}")
    private String createAuthorQueueName;

    @Value("${queue.delete.author.name}")
    private String deleteAuthorQueueName;

    @Value("${queue.update.author.name}")
    private String updateAuthorQueueName;

    @Value("${queue.update.book.name}")
    private String updateBookQueueName;

    @Value("${queue.create.book.name}")
    private String createBookQueueName;

    @Value("${queue.delete.book.name}")
    private String deleteBookQueueName;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Bean("createAuthorQueue")
    public Queue createAuthorQueue() {
        return new Queue(createAuthorQueueName);
    }

    @Bean("updateAuthorQueue")
    public Queue updateAuthorQueue() {
        return new Queue(updateAuthorQueueName);
    }

    @Bean("deleteAuthorQueue")
    public Queue deleteAuthorQueue() {
        return new Queue(deleteAuthorQueueName);
    }

    @Bean("createBookQueue")
    public Queue createBookQueue() {
        return new Queue(createBookQueueName);
    }

    @Bean("updateBookQueue")
    public Queue updateBookQueue() {
        return new Queue(updateBookQueueName);
    }

    @Bean("deleteBookQueue")
    public Queue deleteBookQueue() {
        return new Queue(deleteBookQueueName);
    }

    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory());
    }


    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
