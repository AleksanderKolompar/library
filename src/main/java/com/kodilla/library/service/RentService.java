package com.kodilla.library.service;

import com.kodilla.library.controllers.exceptions.*;
import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.Reader;
import com.kodilla.library.domain.Rent;
import com.kodilla.library.domain.dto.RentRequest;
import com.kodilla.library.domain.dto.RentResponse;
import com.kodilla.library.mapper.RentMapper;
import com.kodilla.library.repository.ReaderRepository;
import com.kodilla.library.repository.RentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class RentService {

    private final RentRepository rentRepository;
    private final BookService bookService;
    private final ReaderRepository readerRepository;
    private final RentMapper rentMapper;

    public RentService(RentRepository rentRepository, BookService bookService, ReaderRepository readerRepository, RentMapper rentMapper) {
        this.rentRepository = rentRepository;
        this.bookService = bookService;
        this.readerRepository = readerRepository;
        this.rentMapper = rentMapper;
    }

    public RentResponse get(Long id)
            throws RentNotFoundException {
        Rent rent = rentRepository.findById(id).orElseThrow(RentNotFoundException::new);
        return rentMapper.mapToRentResponse(rent);
    }

    public List<RentResponse> get() {
        List<Rent> rentList = rentRepository.findAll();
        return rentMapper.mapToRentResponseList(rentList);
    }

    @Transactional
    public RentResponse rentBook(RentRequest rentRequest)
            throws InvalidStatusException, BookNotFoundException, ReaderNotFoundException {
        Book book = bookService.rentBook(rentRequest.getBookId());
        Reader reader = readerRepository.findById(rentRequest.getReaderId())
                .orElseThrow(ReaderNotFoundException::new);
        Rent rent = new Rent(book, reader);
        rent.setRentDate(LocalDate.now());
        rent = rentRepository.save(rent);
        return rentMapper.mapToRentResponse(rent);
    }

    @Transactional
    public RentResponse returnBook(Long id)
            throws InvalidStatusException, BookNotFoundException, RentNotFoundException {
        Rent rent = rentRepository.findById(id)
                .orElseThrow(RentNotFoundException::new);
        Book book = bookService.returnBook(rent.getBook().getId());
        rent.setBook(book);
        rent.setReturnDate(LocalDate.now());
        rent.setId(id);
        rent = rentRepository.save(rent);
        return rentMapper.mapToRentResponse(rent);
    }
}
