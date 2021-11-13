package com.kodilla.library.controllers;

import com.kodilla.library.controllers.exceptions.BookNotFoundException;
import com.kodilla.library.controllers.exceptions.TitleNotFoundException;
import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.Title;
import com.kodilla.library.domain.dto.BookRequest;
import com.kodilla.library.domain.dto.BookResponse;
import com.kodilla.library.mapper.BookMapper;
import com.kodilla.library.mapper.TitleMapper;
import com.kodilla.library.service.BookService;
import com.kodilla.library.service.TitleService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library/book")
public class BookController {

    private BookService bookService;
    private BookMapper bookMapper;
    private TitleService titleService;

    public BookController(BookService bookService, BookMapper bookMapper, TitleService titleService) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
        this.titleService = titleService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public BookResponse saveBook(@RequestBody BookRequest bookRequest) throws TitleNotFoundException {
        Title title = titleService.get(bookRequest.getTitleId());
        Book book = new Book(title);
        book.setStatus(Book.Status.AVAILABLE);
        book = bookService.save(book);
        return bookMapper.mapToBookResponse(book);
    }

    @GetMapping("/{id}")
    public BookResponse getBook(@PathVariable("id") Long id) {
        Book book = bookService.get(id);
        return bookMapper.mapToBookResponse(book);
    }

    @GetMapping()
    public List<BookResponse> getBooks() {
        List<Book> books = bookService.get();
        return bookMapper.mapToBookResponseList(books);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    public BookResponse updateBook(@RequestBody Book.Status status, @PathVariable("id") Long id)
            throws BookNotFoundException {
        Book book = bookService.get(id);
        book.setStatus(status);
        book = bookService.markBook(book);
        return bookMapper.mapToBookResponse(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        bookService.delete(id);
    }

}
