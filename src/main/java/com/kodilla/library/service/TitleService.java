package com.kodilla.library.service;

import com.kodilla.library.controllers.exceptions.TitleExistsException;
import com.kodilla.library.controllers.exceptions.TitleNotFoundException;
import com.kodilla.library.domain.Title;
import com.kodilla.library.repository.TitleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitleService {

    private TitleRepository titleRepository;

    public TitleService(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    public Title get(Long id) throws TitleNotFoundException {
        return titleRepository.findById(id).orElseThrow(TitleNotFoundException::new);
    }

    public List<Title> get() {
        return titleRepository.findAll();
    }

    public Title save(Title title) throws TitleExistsException {
        Long id = title.getId();
        if (id != null && titleRepository.existsById(id)) {
            throw new TitleExistsException();
        }
        return titleRepository.save(title);
    }

    public Title update(Title title) throws TitleNotFoundException {
        Long id = title.getId();
        if (id == null || !titleRepository.existsById(id)) {
            throw new TitleNotFoundException();
        }
        return titleRepository.save(title);
    }

    public void delete(Long id) throws TitleNotFoundException {
        if (id == null) {
            throw new TitleNotFoundException();
        }
        titleRepository.deleteById(id);
    }
}
