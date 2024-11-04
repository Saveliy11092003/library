package ru.trushkov.library.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.trushkov.library.mapper.AuthorMapper;
import ru.trushkov.library.model.dto.AuthorDto;
import ru.trushkov.library.model.entity.Author;
import ru.trushkov.library.repository.AuthorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @RabbitListener(queues = "${queue.create.author.name}")
    public void createAuthor(AuthorDto authorDto) {
        Author author = authorMapper.authorDtoToAuthor(authorDto);
        authorRepository.save(author);
    }

    public AuthorDto getAuthor(Integer id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author with this id does not exist"));
        return authorMapper.authorToAuthorDto(author);
    }


    @Transactional
    @RabbitListener(queues = "${queue.update.author.name}")
    public void updateAuthor(AuthorDto authorDto) {
        Author author = authorRepository.findById(authorDto.getId())
                .orElseThrow(() -> new RuntimeException("Author with this id does not exist"));
        authorMapper.updateAuthorFromAuthorDto(authorDto, author);
        authorRepository.save(author);
    }

    @RabbitListener(queues = "${queue.delete.author.name}")
    public void deleteAuthor(Integer id) {
        authorRepository.deleteById(id);
    }

    public List<AuthorDto> getAllAuthors() {
        return authorRepository.findAll().stream().map(authorMapper::authorToAuthorDto).toList();
    }

}
