package com.kodilla.library.controllers;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.dto.TitleDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/library/title")
public class TitleController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public TitleDto saveTitle(@RequestBody TitleDto titleDto) {
        return titleDto;
    }

    @GetMapping("/{id}")
    public TitleDto getTitle(@PathVariable("id") Long id) {
        List<Book> bookList = new ArrayList<>();
        return new TitleDto(id, "title", "author", 1997, bookList);
    }

    @GetMapping()
    public List<TitleDto> getTitles() {
        List<Book> bookList = new ArrayList<>();
        return List.of(new TitleDto(5L, "title", "author", 1997, bookList));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public TitleDto updateTitle(@RequestBody TitleDto titleDto) {
        List<Book> bookList = new ArrayList<>();
        return new TitleDto(5L, "title", "author", 1997, bookList);
    }

    @DeleteMapping("/{id}")
    public void deleteTitle(@PathVariable("id") Long id) {
        System.out.println("deleted title " + id);
    }

}
