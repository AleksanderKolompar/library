package com.kodilla.library.mapper;

import com.kodilla.library.controllers.exceptions.BookNotFoundException;
import com.kodilla.library.controllers.exceptions.ReaderNotFoundException;
import com.kodilla.library.domain.Rent;
import com.kodilla.library.domain.dto.RentRequest;
import com.kodilla.library.domain.dto.RentResponse;
import com.kodilla.library.service.BookService;
import com.kodilla.library.service.ReaderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentMapper {

    private BookMapper bookMapper;
    private ReaderMapper readerMapper;
    private BookService bookService;
    private ReaderService readerService;

    public RentMapper(BookMapper bookMapper, ReaderMapper readerMapper, BookService bookService, ReaderService readerService) {
        this.bookMapper = bookMapper;
        this.readerMapper = readerMapper;
        this.bookService = bookService;
        this.readerService = readerService;
    }

    public RentResponse mapToRentResponse(Rent rent) {
        RentResponse rentResponse = new RentResponse();
        rentResponse.setId(rent.getId());
        rentResponse.setReaderResponse(readerMapper.mapToReaderResponse(rent.getReader()));
        rentResponse.setBookResponse(bookMapper.mapToBookResponse(rent.getBook()));
        return rentResponse;
    }

    public Rent mapToRent(RentRequest rentRequest) throws BookNotFoundException, ReaderNotFoundException {
        Rent rent = new Rent();
        rent.setBook(bookService.get(rentRequest.getBookId()));
        rent.setReader(readerService.get(rentRequest.getReaderId()));
        return rent;
    }

    public List<RentResponse> mapToRentResponseList(List<Rent> rentList) {
        return rentList.stream().map(this::mapToRentResponse).collect(Collectors.toList());
    }

    public List<Rent> mapToRentsList(List<RentRequest> rentRequestList) {
        return rentRequestList.stream().map(this::mapToRent).collect(Collectors.toList());
    }
}
