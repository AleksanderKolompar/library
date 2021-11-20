package com.kodilla.library.controllers;

import com.kodilla.library.controllers.exceptions.BookNotFoundException;
import com.kodilla.library.controllers.exceptions.TitleNotFoundException;
import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.dto.BookResponse;
import com.kodilla.library.domain.dto.BookSaveRequest;
import com.kodilla.library.domain.dto.BookUpdateRequest;
import com.kodilla.library.service.BookService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public BookResponse saveBook(@RequestBody BookSaveRequest bookSaveRequest)
            throws TitleNotFoundException {
        return bookService.save(bookSaveRequest);
    }

    @GetMapping("/{id}")
    public BookResponse getBook(@PathVariable("id") Long id) {
        return bookService.get(id);
    }

    @GetMapping()
    public List<BookResponse> getBooks() {
        return bookService.get();
    }

    @GetMapping("available/{title_id}")
    public List<BookResponse> findAvailable(@PathVariable("title_id") Long titleId) {
        return bookService.findAvailable(titleId, Book.Status.AVAILABLE);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public BookResponse updateBook(@RequestBody BookUpdateRequest bookUpdateRequest)
            throws BookNotFoundException {
        return bookService.markBook(bookUpdateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        bookService.delete(id);
    }

}
