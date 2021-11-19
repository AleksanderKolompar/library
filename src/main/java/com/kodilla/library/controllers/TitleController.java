package com.kodilla.library.controllers;

import com.kodilla.library.controllers.exceptions.TitleExistsException;
import com.kodilla.library.controllers.exceptions.TitleNotFoundException;
import com.kodilla.library.domain.dto.TitleResponse;
import com.kodilla.library.domain.dto.TitleSaveRequest;
import com.kodilla.library.domain.dto.TitleUpdateRequest;
import com.kodilla.library.service.TitleService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library/title")
public class TitleController {

    private final TitleService titleService;

    public TitleController(TitleService titleService) {
        this.titleService = titleService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public TitleResponse saveTitle(@RequestBody TitleSaveRequest titleSaveRequest) {
        return titleService.save(titleSaveRequest);
    }

    @GetMapping("/{id}")
    public TitleResponse getTitle(@PathVariable("id") Long id)
            throws TitleNotFoundException {
        return titleService.get(id);
    }

    @GetMapping()
    public List<TitleResponse> getTitles() {
        return titleService.get();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public TitleResponse updateTitle(@RequestBody TitleUpdateRequest titleUpdateRequest)
            throws TitleExistsException {
        return titleService.update(titleUpdateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteTitle(@PathVariable("id") Long id) {
        titleService.delete(id);
    }

}
