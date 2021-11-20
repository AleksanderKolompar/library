package com.kodilla.library.controllers;

import com.kodilla.library.controllers.exceptions.ReaderExistsException;
import com.kodilla.library.controllers.exceptions.ReaderNotFoundException;
import com.kodilla.library.domain.dto.ReaderResponse;
import com.kodilla.library.domain.dto.ReaderSaveRequest;
import com.kodilla.library.domain.dto.ReaderUpdateRequest;
import com.kodilla.library.service.ReaderService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library/reader")
public class ReaderController {

    private final ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReaderResponse saveReader(@RequestBody ReaderSaveRequest readerSaveRequest) {
        return readerService.save(readerSaveRequest);
    }

    @GetMapping("/{id}")
    public ReaderResponse getReader(@PathVariable("id") Long id)
            throws ReaderNotFoundException {
        return readerService.get(id);
    }

    @GetMapping()
    public List<ReaderResponse> getReaders() {
        return readerService.get();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReaderResponse updateReader(@RequestBody ReaderUpdateRequest readerUpdateRequest)
            throws ReaderExistsException {
        return readerService.update(readerUpdateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteReader(@PathVariable("id") Long id) {
        readerService.delete(id);
    }

}
