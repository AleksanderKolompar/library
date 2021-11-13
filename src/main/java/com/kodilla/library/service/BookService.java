package com.kodilla.library.service;

import com.kodilla.library.controllers.exceptions.BookNotFoundException;
import com.kodilla.library.controllers.exceptions.InvalidStatusException;
import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.Title;
import com.kodilla.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book get(Long id) throws BookNotFoundException {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    public List<Book> get() {
        return bookRepository.findAll();
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public Book rentBook(Book book) throws InvalidStatusException {
        if (!book.getStatus().equals(Book.Status.AVAILABLE)) {
            throw new InvalidStatusException();
        }
        book.setStatus(Book.Status.RENTED);
        return bookRepository.save(book);
    }

    public Book returnBook(Book book) throws InvalidStatusException {
        if (book.getStatus().equals(Book.Status.AVAILABLE)) {
            throw new InvalidStatusException();
        }
        book.setStatus(Book.Status.AVAILABLE);
        return bookRepository.save(book);
    }

    public Book markBook(Book book) {
        book.setStatus(Book.Status.LOST);
        return bookRepository.save(book);
    }

    public Book markDestroyedBook(Book book) {
        book.setStatus(Book.Status.DESTROYED);
        return bookRepository.save(book);
    }

    public List<Book> findAvailable(Title title) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getStatus().equals(Book.Status.AVAILABLE))
                .collect(Collectors.toList());
    }
}
