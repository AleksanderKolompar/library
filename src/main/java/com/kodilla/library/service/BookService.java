package com.kodilla.library.service;

import com.kodilla.library.controllers.exceptions.BookNotFoundException;
import com.kodilla.library.controllers.exceptions.InvalidStatusException;
import com.kodilla.library.controllers.exceptions.TitleNotFoundException;
import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.Title;
import com.kodilla.library.domain.dto.BookResponse;
import com.kodilla.library.domain.dto.BookSaveRequest;
import com.kodilla.library.domain.dto.BookUpdateRequest;
import com.kodilla.library.mapper.BookMapper;
import com.kodilla.library.repository.BookRepository;
import com.kodilla.library.repository.TitleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kodilla.library.domain.Book.Status.AVAILABLE;
import static com.kodilla.library.domain.Book.Status.RENTED;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final TitleRepository titleRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, TitleRepository titleRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.titleRepository = titleRepository;
        this.bookMapper = bookMapper;
    }

    public BookResponse get(Long id) throws BookNotFoundException {
        Book book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        return bookMapper.mapToBookResponse(book);
    }

    public List<BookResponse> get() {
        List<Book> bookList = bookRepository.findAll();
        return bookMapper.mapToBookResponseList(bookList);
    }

    public BookResponse save(BookSaveRequest bookSaveRequest)
            throws TitleNotFoundException {
        Title title = titleRepository.findById(bookSaveRequest.getTitleId())
                .orElseThrow(TitleNotFoundException::new);
        Book book = bookRepository.save(new Book(title, Book.Status.AVAILABLE));
        return bookMapper.mapToBookResponse(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public Book rentBook(Long id)
            throws InvalidStatusException, BookNotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        if (!book.getStatus().equals(Book.Status.AVAILABLE)) {
            throw new InvalidStatusException(Book.Status.AVAILABLE, book.getStatus());
        }
        BookUpdateRequest bookUpdateRequest = new BookUpdateRequest(book.getId(), RENTED);
        return updateBook(bookUpdateRequest);
    }

    public Book returnBook(Long id)
            throws InvalidStatusException, BookNotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        if (book.getStatus().equals(Book.Status.AVAILABLE)) {
            throw new InvalidStatusException(RENTED, book.getStatus());
        }
        BookUpdateRequest bookUpdateRequest = new BookUpdateRequest(book.getId(), AVAILABLE);
        return updateBook(bookUpdateRequest);
    }

    public BookResponse markBook(BookUpdateRequest bookUpdateRequest)
            throws BookNotFoundException {
        Book book = updateBook(bookUpdateRequest);
        return bookMapper.mapToBookResponse(book);
    }

    public Book updateBook(BookUpdateRequest bookUpdateRequest)
            throws BookNotFoundException {
        Book book = bookRepository.findById(bookUpdateRequest.getId())
                .orElseThrow(BookNotFoundException::new);
        book.setStatus(bookUpdateRequest.getStatus());
        book = bookRepository.save(book);
        return book;
    }

    public List<BookResponse> findBookWithStatus(Long titleId, Book.Status status) {
        return bookMapper.mapToBookResponseList(bookRepository.findBookByTitleIdAndStatus(titleId, status));
    }
}
