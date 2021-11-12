package com.kodilla.library.mapper;

import com.kodilla.library.domain.Rent;
import com.kodilla.library.domain.dto.BookRequest;
import com.kodilla.library.domain.dto.ReaderRequest;
import com.kodilla.library.domain.dto.RentRequest;
import com.kodilla.library.domain.dto.RentResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentMapper {

    BookMapper bookMapper;
    ReaderMapper readerMapper;

    public RentResponse mapToRentResponse(Rent rent) {
        RentResponse rentResponse = new RentResponse();
        rentResponse.setId(rent.getId());
        rentResponse.setReaderResponse(readerMapper.mapToReaderResponse(rent.getReader()));
        rentResponse.setBookResponse(bookMapper.mapToBookResponse(rent.getBook()));
        return rentResponse;
    }

    public Rent mapToRent(RentRequest rentRequest) {
        Rent rent = new Rent();
        rent.setBook(bookMapper.mapToBook(new BookRequest()));

        rent.setReader(readerMapper.mapToReader(new ReaderRequest()));

        return rent;
    }

    public List<RentResponse> mapToRentResponseList(List<Rent> rentList) {
        return rentList.stream().map(this::mapToRentResponse).collect(Collectors.toList());
    }

    public List<Rent> mapToRentsList(List<RentRequest> rentRequestList) {
        return rentRequestList.stream().map(this::mapToRent).collect(Collectors.toList());
    }
}
