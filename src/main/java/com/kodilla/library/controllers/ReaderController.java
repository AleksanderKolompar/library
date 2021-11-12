package com.kodilla.library.controllers;

import com.kodilla.library.domain.dto.ReaderRequest;
import com.kodilla.library.domain.dto.ReaderResponse;
import com.kodilla.library.domain.dto.RentResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/library/reader")
public class ReaderController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReaderRequest saveReader(@RequestBody ReaderRequest readerRequest) {
        return readerRequest;
    }

    @GetMapping("/{id}")
    public ReaderResponse getReader(@PathVariable("id") Long id) {
        List<RentResponse> rentList = new ArrayList<>();
        return new ReaderResponse(id, "firstname", "lastname", rentList);
    }

    @GetMapping()
    public List<ReaderResponse> getReaders() {
        List<RentResponse> rentList = new ArrayList<>();
        return List.of(new ReaderResponse(5L, "firstname", "lastname", rentList));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReaderResponse updateReader(@RequestBody ReaderResponse titleDto) {
        List<RentResponse> rentList = new ArrayList<>();
        return new ReaderResponse(5L, "firstname", "lastname", rentList);
    }

    @DeleteMapping("/{id}")
    public void deleteReader(@PathVariable("id") Long id) {
        System.out.println("deleted reader " + id);
    }

}
