package ru.trushkov.library.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trushkov.library.mapper.BookMapper;
import ru.trushkov.library.model.dto.BookDto;
import ru.trushkov.library.model.entity.Author;
import ru.trushkov.library.model.entity.Book;
import ru.trushkov.library.repository.AuthorRepository;
import ru.trushkov.library.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;

    public void createBook(BookDto bookDto) {
        Book book = bookMapper.bookDtoToBook(bookDto);
        Book savedBook = bookRepository.save(book);
        bookDto.setId(savedBook.getId());
    }

    public BookDto getBook(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book with this id does not exist"));
        return bookMapper.bookToBookDto(book);
    }

    @Transactional
    public void updateBook(BookDto bookDto, Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book with this id does not exist"));
        bookMapper.updateBookFromBookDto(bookDto, book);
        Author author = authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author with this id does not exist"));
        book.setAuthor(author);
        bookRepository.save(book);
        bookDto.setId(id);
    }

    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream().map(bookMapper::bookToBookDto).toList();
    }

}
