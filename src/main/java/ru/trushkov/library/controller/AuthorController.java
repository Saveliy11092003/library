package ru.trushkov.library.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.trushkov.library.model.Response;
import ru.trushkov.library.model.dto.AuthorDto;
import ru.trushkov.library.service.AuthorMessageSender;
import ru.trushkov.library.service.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/authors")
public class AuthorController {
    private final AuthorService authorService;
    private final AuthorMessageSender authorMessageSender;

    @PostMapping()
    public ResponseEntity<Void> createAuthor(@RequestBody @Valid AuthorDto authorDto) {
        authorMessageSender.sendMessageCreate(authorDto);
        return Response.sendCreated();
    }

    @GetMapping("/{id}")
    public AuthorDto getAuthorById(@PathVariable("id") Integer id) {
        return authorService.getAuthor(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable("id") Integer id, @RequestBody @Valid AuthorDto authorDto) {
        authorMessageSender.sendMessageUpdate(authorDto, id);
        return Response.sendOk(authorDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("id") Integer id) {
        authorMessageSender.sendMessageDelete(id);
        return Response.sendNoContent();
    }

    @GetMapping()
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        return Response.sendOk(authorService.getAllAuthors());
    }

}
