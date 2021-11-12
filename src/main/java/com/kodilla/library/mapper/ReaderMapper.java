package com.kodilla.library.mapper;

import com.kodilla.library.domain.Reader;
import com.kodilla.library.domain.dto.ReaderRequest;
import com.kodilla.library.domain.dto.ReaderResponse;
import org.springframework.stereotype.Service;

@Service
public class ReaderMapper {

    RentMapper rentMapper;


    public ReaderResponse mapToReaderResponse(Reader reader) {
        ReaderResponse readerResponse = new ReaderResponse();
        readerResponse.setId(reader.getId());
        readerResponse.setFirstname(reader.getFirstname());
        readerResponse.setLastname(reader.getLastname());
        readerResponse.setRentResponseList(rentMapper.mapToRentResponseList(reader.getRentList()));
        return readerResponse;
    }

    public Reader mapToReader(ReaderRequest readerRequest) {
        Reader reader = new Reader();
        reader.setFirstname(readerRequest.getFirstname());
        reader.setLastname(readerRequest.getLastname());
        return reader;
    }
}
