package com.kodilla.library.controllers;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.dto.BookDto;
import com.kodilla.library.domain.dto.TitleDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/library/book")
public class BookController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public BookDto saveBook(@RequestBody BookDto bookDto) {
        return bookDto;
    }

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable("id") Long id) {
        List<Book> bookList = new ArrayList<>();
        return new BookDto(id, new TitleDto(id, "title", "author", 1997, bookList), Book.Status.RENTED);
    }

    @GetMapping()
    public List<BookDto> getBooks() {
        List<Book> bookList = new ArrayList<>();
        return List.of(new BookDto(5L, new TitleDto(2L, "title", "author", 1997, bookList), Book.Status.RENTED));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public BookDto updateBook(@RequestBody BookDto titleDto) {
        List<Book> bookList = new ArrayList<>();
        return new BookDto(5L, new TitleDto(2L, "title", "author", 1997, bookList), Book.Status.RENTED);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        System.out.println("deleted book " + id);
    }

}
