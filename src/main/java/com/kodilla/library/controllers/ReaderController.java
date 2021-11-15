package com.kodilla.library.controllers;

import com.kodilla.library.controllers.exceptions.ReaderExistsException;
import com.kodilla.library.controllers.exceptions.ReaderNotFoundException;
import com.kodilla.library.domain.Reader;
import com.kodilla.library.domain.dto.ReaderRequest;
import com.kodilla.library.domain.dto.ReaderResponse;
import com.kodilla.library.mapper.ReaderMapper;
import com.kodilla.library.service.ReaderService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/library/reader")
public class ReaderController {

    private ReaderMapper readerMapper;
    private ReaderService readerService;

    public ReaderController(ReaderMapper readerMapper, ReaderService readerService) {
        this.readerMapper = readerMapper;
        this.readerService = readerService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReaderResponse saveReader(@RequestBody ReaderRequest readerRequest) {
        Reader reader = readerMapper.mapToReader(readerRequest);
        reader = readerService.save(reader);
        return readerMapper.mapToReaderResponse(reader);
    }

    @GetMapping("/{id}")
    public ReaderResponse getReader(@PathVariable("id") Long id) throws ReaderNotFoundException {
        Reader reader = readerService.get(id);
        return readerMapper.mapToReaderResponse(reader);
    }

    @GetMapping()
    public List<ReaderResponse> getReaders() {
        List<Reader> readers = readerService.get();
        return readerMapper.mapToReaderResponseList(readers);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    public ReaderResponse updateReader(@RequestBody ReaderRequest readerRequest, @PathVariable("id") Long id)
            throws ReaderExistsException {
        Reader reader = readerMapper.mapToReader(readerRequest);
        reader.setId(id);
        reader = readerService.update(reader);
        return readerMapper.mapToReaderResponse(reader);
    }

    @DeleteMapping("/{id}")
    public void deleteReader(@PathVariable("id") Long id) {
        readerService.delete(id);
    }

}
