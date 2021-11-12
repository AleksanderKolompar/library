package com.kodilla.library.controllers;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.dto.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/library/rent")
public class RentController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public RentRequest saveRent(@RequestBody RentRequest rentRequest) {
        return rentRequest;
    }

    @GetMapping("/{id}")
    public RentResponse getRent(@PathVariable("id") Long id) {
        List<Book> bookList = new ArrayList<>();
        List<RentResponse> rentList = new ArrayList<>();
        BookResponse bookResponse = new BookResponse(id, new TitleResponse(id, "title", "author", 1997, bookList), "rented");
        ReaderResponse readerResponse = new ReaderResponse(id, "firstname", "lastname", rentList);
        return new RentResponse(id, bookResponse, readerResponse);
    }

    @GetMapping()
    public List<RentResponse> getRents() {
        List<Book> bookList = new ArrayList<>();
        List<RentResponse> rentList = new ArrayList<>();
        BookResponse bookResponse = new BookResponse(5L, new TitleResponse(5L, "title", "author", 1997, bookList), "rented");
        ReaderResponse readerResponse = new ReaderResponse(5L, "firstname", "lastname", rentList);
        return List.of(new RentResponse(5L, bookResponse, readerResponse));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public RentResponse updateRent(@RequestBody RentResponse rentResponse) {
        List<Book> bookList = new ArrayList<>();
        List<RentResponse> rentList = new ArrayList<>();
        BookResponse bookResponse = new BookResponse(5L, new TitleResponse(5L, "title", "author", 1997, bookList), "rented");
        ReaderResponse readerResponse = new ReaderResponse(5L, "firstname", "lastname", rentList);
        return new RentResponse(5L, bookResponse, readerResponse);
    }

    @DeleteMapping("/{id}")
    public void deleteRent(@PathVariable("id") Long id) {
        System.out.println("deleted rent " + id);
    }

}
