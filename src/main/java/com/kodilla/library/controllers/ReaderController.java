package com.kodilla.library.controllers;

import com.kodilla.library.domain.dto.ReaderDto;
import com.kodilla.library.domain.dto.RentDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/library/reader")
public class ReaderController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReaderDto saveReader(@RequestBody ReaderDto readerDto) {
        return readerDto;
    }

    @GetMapping("/{id}")
    public ReaderDto getReader(@PathVariable("id") Long id) {
        List<RentDto> rentList = new ArrayList<>();
        return new ReaderDto(id, "firstname", "lastname", rentList);
    }

    @GetMapping()
    public List<ReaderDto> getReaders() {
        List<RentDto> rentList = new ArrayList<>();
        return List.of(new ReaderDto(5L, "firstname", "lastname", rentList));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReaderDto updateReader(@RequestBody ReaderDto titleDto) {
        List<RentDto> rentList = new ArrayList<>();
        return new ReaderDto(5L, "firstname", "lastname", rentList);
    }

    @DeleteMapping("/{id}")
    public void deleteReader(@PathVariable("id") Long id) {
        System.out.println("deleted reader " + id);
    }

}
