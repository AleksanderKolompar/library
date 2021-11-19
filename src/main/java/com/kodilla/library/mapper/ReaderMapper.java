package com.kodilla.library.mapper;

import com.kodilla.library.domain.Reader;
import com.kodilla.library.domain.dto.ReaderResponse;
import com.kodilla.library.domain.dto.ReaderSaveRequest;
import com.kodilla.library.domain.dto.ReaderUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReaderMapper {

    public ReaderResponse mapToReaderResponse(Reader reader) {
        ReaderResponse readerResponse = new ReaderResponse();
        readerResponse.setId(
                reader.getId());
        readerResponse.setFirstname(
                reader.getFirstname());
        readerResponse.setLastname(
                reader.getLastname());
        readerResponse.setRegistrationDate(
                reader.getRegistrationDate());
        return readerResponse;
    }

    public Reader mapToReader(ReaderSaveRequest readerSaveRequest) {
        return new Reader(readerSaveRequest.getFirstname(), readerSaveRequest.getLastname());
    }

    public Reader mapToReader(ReaderUpdateRequest readerUpdateRequest) {
        return new Reader(
                readerUpdateRequest.getId(),
                readerUpdateRequest.getFirstname(),
                readerUpdateRequest.getLastname());
    }

    public List<ReaderResponse> mapToReaderResponseList(List<Reader> readers) {
        return readers.stream()
                .map(this::mapToReaderResponse)
                .collect(Collectors.toList());
    }
}
