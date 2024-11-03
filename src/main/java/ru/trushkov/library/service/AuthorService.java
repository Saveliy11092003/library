package ru.trushkov.library.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

    public void createAuthor(AuthorDto authorDto) {
        Author author = authorMapper.authorDtoToAuthor(authorDto);
        Author savedAuthor = authorRepository.save(author);
        authorDto.setId(savedAuthor.getId());
    }

    public AuthorDto getAuthor(Integer id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author with this id does not exist"));
        return authorMapper.authorToAuthorDto(author);
    }

    @Transactional
    public void updateAuthor(AuthorDto authorDto, Integer id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author with this id does not exist"));
        authorMapper.updateAuthorFromAuthorDto(authorDto, author);
        authorRepository.save(author);
        authorDto.setId(id);
    }

    public void deleteAuthor(Integer id) {
        authorRepository.deleteById(id);
    }

    public List<AuthorDto> getAllAuthors() {
        return authorRepository.findAll().stream().map(authorMapper::authorToAuthorDto).toList();
    }

}
