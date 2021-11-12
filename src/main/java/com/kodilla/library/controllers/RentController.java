package com.kodilla.library.controllers;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.dto.BookDto;
import com.kodilla.library.domain.dto.ReaderDto;
import com.kodilla.library.domain.dto.RentDto;
import com.kodilla.library.domain.dto.TitleDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/library/rent")
public class RentController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public RentDto saveRent(@RequestBody RentDto rentDto) {
        return rentDto;
    }

    @GetMapping("/{id}")
    public RentDto getRent(@PathVariable("id") Long id) {
        List<Book> bookList = new ArrayList<>();
        List<RentDto> rentList = new ArrayList<>();
        BookDto bookDto = new BookDto(id, new TitleDto(id, "title", "author", 1997, bookList), Book.Status.RENTED);
        ReaderDto readerDto = new ReaderDto(id, "firstname", "lastname", rentList);
        return new RentDto(id, bookDto, readerDto);
    }

    @GetMapping()
    public List<RentDto> getRents() {
        List<Book> bookList = new ArrayList<>();
        List<RentDto> rentList = new ArrayList<>();
        BookDto bookDto = new BookDto(5L, new TitleDto(5L, "title", "author", 1997, bookList), Book.Status.RENTED);
        ReaderDto readerDto = new ReaderDto(5L, "firstname", "lastname", rentList);
        return List.of(new RentDto(5L, bookDto, readerDto));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public RentDto updateRent(@RequestBody RentDto rentDto) {
        List<Book> bookList = new ArrayList<>();
        List<RentDto> rentList = new ArrayList<>();
        BookDto bookDto = new BookDto(5L, new TitleDto(5L, "title", "author", 1997, bookList), Book.Status.RENTED);
        ReaderDto readerDto = new ReaderDto(5L, "firstname", "lastname", rentList);
        return new RentDto(5L, bookDto, readerDto);
    }

    @DeleteMapping("/{id}")
    public void deleteRent(@PathVariable("id") Long id) {
        System.out.println("deleted rent " + id);
    }

}
