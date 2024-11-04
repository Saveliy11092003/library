package ru.trushkov.library.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.trushkov.library.model.dto.BookDto;

@Setter
@Service
@RequiredArgsConstructor
public class BookMessageSender {

    private final AmqpTemplate amqpTemplate;

    @Value("${queue.create.book.name}")
    private String createQueueName;

    @Value("${queue.update.book.name}")
    private String updateQueueName;

    @Value("${queue.delete.book.name}")
    private String deleteQueueName;

    public void sendMessageCreate(BookDto bookDto) {
        amqpTemplate.convertAndSend(createQueueName, bookDto);
    }

    public void sendMessageUpdate(BookDto bookDto, Integer id) {
        bookDto.setId(id);
        amqpTemplate.convertAndSend(updateQueueName, bookDto);
    }

    public void sendMessageDelete(Integer id) {
        amqpTemplate.convertAndSend(deleteQueueName, id);
    }

}
