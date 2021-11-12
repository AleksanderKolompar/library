package com.kodilla.library.controllers;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.dto.TitleRequest;
import com.kodilla.library.domain.dto.TitleResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/library/title")
public class TitleController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public TitleRequest saveTitle(@RequestBody TitleRequest titleRequest) {
        return titleRequest;
    }

    @GetMapping("/{id}")
    public TitleResponse getTitle(@PathVariable("id") Long id) {
        List<Book> bookList = new ArrayList<>();
        return new TitleResponse(id, "title", "author", 1997, bookList);
    }

    @GetMapping()
    public List<TitleResponse> getTitles() {
        List<Book> bookList = new ArrayList<>();
        return List.of(new TitleResponse(5L, "title", "author", 1997, bookList));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public TitleResponse updateTitle(@RequestBody TitleResponse titleResponse) {
        List<Book> bookList = new ArrayList<>();
        return new TitleResponse(5L, "title", "author", 1997, bookList);
    }

    @DeleteMapping("/{id}")
    public void deleteTitle(@PathVariable("id") Long id) {
        System.out.println("deleted title " + id);
    }

}
