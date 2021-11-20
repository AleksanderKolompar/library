package com.kodilla.library.service;

import com.kodilla.library.controllers.exceptions.TitleExistsException;
import com.kodilla.library.controllers.exceptions.TitleNotFoundException;
import com.kodilla.library.domain.Title;
import com.kodilla.library.domain.dto.TitleResponse;
import com.kodilla.library.domain.dto.TitleSaveRequest;
import com.kodilla.library.domain.dto.TitleUpdateRequest;
import com.kodilla.library.mapper.TitleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitleService {

    private final com.kodilla.library.repository.TitleRepository titleRepository;
    private final TitleMapper titleMapper;

    public TitleService(com.kodilla.library.repository.TitleRepository titleRepository, TitleMapper titleMapper) {
        this.titleRepository = titleRepository;
        this.titleMapper = titleMapper;
    }

    public TitleResponse get(Long id)
            throws TitleNotFoundException {
        return titleMapper.mapToTitleResponse(
                titleRepository.findById(id).orElseThrow(TitleNotFoundException::new));
    }

    public List<TitleResponse> get() {
        return titleMapper.mapToTitleResponseList(titleRepository.findAll());
    }

    public TitleResponse save(TitleSaveRequest titleSaveRequest) {
        Title title = titleMapper.mapToTitle(titleSaveRequest);
        title = titleRepository.save(title);
        return titleMapper.mapToTitleResponse(title);
    }

    public TitleResponse update(TitleUpdateRequest titleUpdateRequest)
            throws TitleExistsException {
        Title title = titleMapper.mapToTitle(titleUpdateRequest);
        if (!titleRepository.existsById(title.getId())) {
            throw new TitleNotFoundException();
        }
        title = titleRepository.save(title);
        return titleMapper.mapToTitleResponse(title);
    }

    public void delete(Long id) throws TitleNotFoundException {
        if (id == null) {
            throw new TitleNotFoundException();
        }
        titleRepository.deleteById(id);
    }
}
