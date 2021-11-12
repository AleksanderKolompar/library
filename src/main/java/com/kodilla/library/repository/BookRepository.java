package com.kodilla.library.repository;

import com.kodilla.library.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {

    @Override
    Book save(Book book);

    @Override
    Optional<Book> findById(Long id);

    @Override
    List<Book> findAll();

    @Override
    void deleteById(Long id);
}
