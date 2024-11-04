package ru.trushkov.library.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.trushkov.library.model.dto.BookDto;
import ru.trushkov.library.model.entity.Author;
import ru.trushkov.library.model.entity.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book bookDtoToBookDefault(BookDto bookDto);
    BookDto bookToBookDtoDefault(Book book);

    default Book bookDtoToBook(BookDto bookDto) {
        Book book = bookDtoToBookDefault(bookDto);
        book.setAuthor(Author.builder().id(bookDto.getAuthorId()).build());
        return book;
    }

    default BookDto bookToBookDto(Book book) {
        BookDto bookDto = bookToBookDtoDefault(book);
        bookDto.setAuthorId(book.getAuthor().getId());
        return bookDto;
    }

    @Mapping(target = "id", ignore = true)
    void updateBookFromBookDto(BookDto bookDto, @MappingTarget Book book);

}
