package ru.trushkov.library.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.trushkov.library.model.dto.AuthorDto;

@Setter
@Service
@RequiredArgsConstructor
public class AuthorMessageSender {

    private final AmqpTemplate amqpTemplate;

    @Value("${queue.create.author.name}")
    private String createQueueName;

    @Value("${queue.delete.author.name}")
    private String deleteQueueName;

    @Value("${queue.update.author.name}")
    private String updateQueueName;

    public void sendMessageCreate(AuthorDto authorDto) {
        amqpTemplate.convertAndSend(createQueueName, authorDto);
    }

    public void sendMessageUpdate(AuthorDto authorDto, Integer id) {
        authorDto.setId(id);
        amqpTemplate.convertAndSend(updateQueueName, authorDto);
    }

    public void sendMessageDelete(Integer id) {
        amqpTemplate.convertAndSend(deleteQueueName, id);
    }

}
