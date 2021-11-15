package com.kodilla.library.mapper;

import com.kodilla.library.domain.Reader;
import com.kodilla.library.domain.dto.ReaderRequest;
import com.kodilla.library.domain.dto.ReaderResponse;
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

    public Reader mapToReader(ReaderRequest readerRequest) {
        Reader reader = new Reader();
        reader.setFirstname(readerRequest.getFirstname());
        reader.setLastname(readerRequest.getLastname());
        return reader;
    }

    public List<ReaderResponse> mapToReaderResponseList(List<Reader> readers) {
        return readers.stream()
                .map(this::mapToReaderResponse)
                .collect(Collectors.toList());
    }
}
