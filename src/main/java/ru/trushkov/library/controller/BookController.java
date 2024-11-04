package ru.trushkov.library.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.trushkov.library.model.Response;
import ru.trushkov.library.model.dto.BookDto;
import ru.trushkov.library.service.BookMessageSender;
import ru.trushkov.library.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;
    private final BookMessageSender bookMessageSender;

    @PostMapping()
    public ResponseEntity<Void> createBook(@RequestBody @Valid BookDto bookDto) {
        bookMessageSender.sendMessageCreate(bookDto);
        return Response.sendCreated();
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable("id") Integer id) {
        return bookService.getBook(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable("id") Integer id, @RequestBody @Valid BookDto bookDto) {
        bookMessageSender.sendMessageUpdate(bookDto, id);
        return Response.sendOk(bookDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Integer id) {
        bookMessageSender.sendMessageDelete(id);
        return Response.sendNoContent();
    }

    @GetMapping()
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return Response.sendOk(bookService.getAllBooks());
    }

}
