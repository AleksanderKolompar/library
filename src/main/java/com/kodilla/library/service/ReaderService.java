package com.kodilla.library.service;

import com.kodilla.library.controllers.exceptions.ReaderExistsException;
import com.kodilla.library.controllers.exceptions.ReaderNotFoundException;
import com.kodilla.library.domain.Reader;
import com.kodilla.library.repository.ReaderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderService {

    private ReaderRepository readerRepository;

    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public Reader get(Long id) throws ReaderNotFoundException {
        return readerRepository.findById(id).orElseThrow(ReaderNotFoundException::new);
    }

    public List<Reader> get() {
        return readerRepository.findAll();
    }

    public Reader save(Reader reader) throws ReaderExistsException {
        Long id = reader.getId();
        if (id != null && readerRepository.existsById(id)) {
            throw new ReaderExistsException();
        }
        return readerRepository.save(reader);
    }

    public Reader update(Reader reader) throws ReaderNotFoundException {
        Long id = reader.getId();
        if (id == null || !readerRepository.existsById(id)) {
            throw new ReaderNotFoundException();
        }
        return readerRepository.save(reader);
    }

    public void delete(Long id) throws ReaderNotFoundException {
        if (id == null){
            throw new ReaderNotFoundException();
        }
        readerRepository.deleteById(id);
    }
}
