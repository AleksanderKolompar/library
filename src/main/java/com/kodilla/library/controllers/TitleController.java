package com.kodilla.library.controllers;

import com.kodilla.library.controllers.exceptions.TitleExistsException;
import com.kodilla.library.controllers.exceptions.TitleNotFoundException;
import com.kodilla.library.domain.Title;
import com.kodilla.library.domain.dto.TitleRequest;
import com.kodilla.library.domain.dto.TitleResponse;
import com.kodilla.library.mapper.TitleMapper;
import com.kodilla.library.service.TitleService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library/title")
public class TitleController {

    private TitleMapper titleMapper;
    private TitleService titleService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public TitleResponse saveTitle(@RequestBody TitleRequest titleRequest) {
        Title title = titleMapper.mapToTitle(titleRequest);
        title = titleService.save(title);
        TitleResponse titleResponse = titleMapper.mapToTitleResponse(title);
        return titleResponse;
    }

    @GetMapping("/{id}")
    public TitleResponse getTitle(@PathVariable("id") Long id) throws TitleNotFoundException {
        Title title = titleService.get(id);
        return titleMapper.mapToTitleResponse(title);
    }

    @GetMapping()
    public List<TitleResponse> getTitles() {
        List<Title> titles = titleService.get();
        return titleMapper.mapToTitleResponseList(titles);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    public TitleResponse updateTitle(@RequestBody TitleRequest titleRequest, @PathVariable("id") Long id)
            throws TitleExistsException {
        Title title = titleMapper.mapToTitle(titleRequest);
        title.setId(id);
        title = titleService.update(title);
        return titleMapper.mapToTitleResponse(title);
    }

    @DeleteMapping("/{id}")
    public void deleteTitle(@PathVariable("id") Long id) {
        titleService.delete(id);
    }

}
