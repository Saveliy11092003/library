package ru.trushkov.library.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.trushkov.library.model.Response;
import ru.trushkov.library.model.dto.AuthorDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {

    @PostMapping()
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody @Valid AuthorDto authorDto) {
        authorService.createAuthor(authorDto);
        return Response.sendOk(authorDto);
    }

    @GetMapping("/{id}")
    public AuthorDto getAuthorById(@PathVariable("id") Integer id) {
        return authorService.getAuthor(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable("id") Integer id, @RequestBody @Valid AuthorDto authorDto) {
        authorService.updateAuthor(authorDto, id);
        return Response.sendOk(authorDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("id") Integer id) {
        authorService.deleteAuthor(id);
        return Response.sendNoContent();
    }

    @GetMapping()
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        return Response.sendOk(authorService.getAllAuthors());
    }

}
