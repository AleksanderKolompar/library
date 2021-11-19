package com.kodilla.library.mapper;

import com.kodilla.library.domain.Rent;
import com.kodilla.library.domain.dto.RentResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentMapper {

    private final BookMapper bookMapper;
    private final ReaderMapper readerMapper;

    public RentMapper(BookMapper bookMapper, ReaderMapper readerMapper) {
        this.bookMapper = bookMapper;
        this.readerMapper = readerMapper;
    }

    public com.kodilla.library.domain.dto.RentResponse mapToRentResponse(Rent rent) {
        com.kodilla.library.domain.dto.RentResponse rentResponse = new com.kodilla.library.domain.dto.RentResponse();
        rentResponse.setId(rent.getId());
        rentResponse.setReaderResponse(readerMapper.mapToReaderResponse(rent.getReader()));
        rentResponse.setBookResponse(bookMapper.mapToBookResponse(rent.getBook()));
        rentResponse.setRentDate(rent.getRentDate());
        rentResponse.setReturnDate(rent.getReturnDate());
        return rentResponse;
    }

    public List<RentResponse> mapToRentResponseList(List<Rent> rentList) {
        return rentList.stream().map(this::mapToRentResponse).collect(Collectors.toList());
    }
}
