package ru.trushkov.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.trushkov.library.model.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
