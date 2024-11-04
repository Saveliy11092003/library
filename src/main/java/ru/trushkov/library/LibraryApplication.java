package ru.trushkov.library;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication {

	@Autowired
	private RabbitAdmin rabbitAdmin;

	@Autowired
	@Qualifier("createAuthorQueue")
	private Queue createAuthorQueue;

	@Autowired
	@Qualifier("updateAuthorQueue")
	private Queue updateAuthorQueue;

	@Autowired
	@Qualifier("deleteAuthorQueue")
	private Queue deleteAuthorQueue;

	@Autowired
	@Qualifier("createBookQueue")
	private Queue createBookQueue;

	@Autowired
	@Qualifier("updateBookQueue")
	private Queue updateBookQueue;

	@Autowired
	@Qualifier("deleteBookQueue")
	private Queue deleteBookQueue;

	@PostConstruct
	public void declareQueue() {
		rabbitAdmin.declareQueue(createAuthorQueue);
		rabbitAdmin.declareQueue(updateAuthorQueue);
		rabbitAdmin.declareQueue(deleteAuthorQueue);
		rabbitAdmin.declareQueue(createBookQueue);
		rabbitAdmin.declareQueue(updateBookQueue);
		rabbitAdmin.declareQueue(deleteBookQueue);
	}

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

}
