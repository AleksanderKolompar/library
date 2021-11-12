package com.kodilla.library.controllers;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.dto.BookRequest;
import com.kodilla.library.domain.dto.BookResponse;
import com.kodilla.library.domain.dto.TitleResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/library/book")
public class BookController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public BookRequest saveBook(@RequestBody BookRequest bookRequest) {
        return bookRequest;
    }

    @GetMapping("/{id}")
    public BookResponse getBook(@PathVariable("id") Long id) {
        List<Book> bookList = new ArrayList<>();
        return new BookResponse(id, new TitleResponse(id, "title", "author", 1997, bookList), "rented");
    }

    @GetMapping()
    public List<BookResponse> getBooks() {
        List<Book> bookList = new ArrayList<>();
        return List.of(new BookResponse(5L, new TitleResponse(2L, "title", "author", 1997, bookList), "rented"));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public BookResponse updateBook(@RequestBody BookResponse bookRequest) {
        List<Book> bookList = new ArrayList<>();
        return new BookResponse(5L, new TitleResponse(2L, "title", "author", 1997, bookList), "rented");
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        System.out.println("deleted book " + id);
    }

}
