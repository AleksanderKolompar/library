package com.kodilla.library.service;

import com.kodilla.library.controllers.exceptions.ReaderNotFoundException;
import com.kodilla.library.domain.Reader;
import com.kodilla.library.domain.dto.ReaderResponse;
import com.kodilla.library.domain.dto.ReaderSaveRequest;
import com.kodilla.library.domain.dto.ReaderUpdateRequest;
import com.kodilla.library.mapper.ReaderMapper;
import com.kodilla.library.repository.ReaderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReaderService {

    private final ReaderRepository readerRepository;
    private final ReaderMapper readerMapper;

    public ReaderService(ReaderRepository readerRepository, ReaderMapper readerMapper) {
        this.readerRepository = readerRepository;
        this.readerMapper = readerMapper;
    }

    public ReaderResponse get(Long id)
            throws ReaderNotFoundException {
        Reader reader = readerRepository.findById(id)
                .orElseThrow(ReaderNotFoundException::new);
        return readerMapper.mapToReaderResponse(reader);
    }

    public List<ReaderResponse> get() {
        return readerMapper.mapToReaderResponseList(readerRepository.findAll());
    }

    public ReaderResponse save(ReaderSaveRequest readerSaveRequest) {
        Reader reader = readerMapper.mapToReader(readerSaveRequest);
        reader.setRegistrationDate(LocalDate.now());
        reader = readerRepository.save(reader);
        return readerMapper.mapToReaderResponse(reader);
    }

    public ReaderResponse update(ReaderUpdateRequest readerUpdateRequest)
            throws ReaderNotFoundException {
        Reader reader = readerMapper.mapToReader(readerUpdateRequest);
        if (!readerRepository.existsById(reader.getId())) {
            throw new ReaderNotFoundException();
        }
        reader = readerRepository.save(reader);
        return readerMapper.mapToReaderResponse(reader);
    }

    public void delete(Long id) {
        readerRepository.deleteById(id);
    }
}
