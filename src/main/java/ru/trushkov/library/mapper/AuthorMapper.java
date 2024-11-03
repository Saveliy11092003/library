package ru.trushkov.library.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.trushkov.library.model.dto.AuthorDto;
import ru.trushkov.library.model.entity.Author;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    Author authorDtoToAuthor(AuthorDto authorDto);
    AuthorDto authorToAuthorDto(Author author);
    @Mapping(target = "id", ignore = true)
    void updateAuthorFromAuthorDto(AuthorDto authorDto, @MappingTarget Author author);

}
