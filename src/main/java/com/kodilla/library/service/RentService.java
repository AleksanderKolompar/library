package com.kodilla.library.service;

import com.kodilla.library.controllers.exceptions.InvalidStatusException;
import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.Rent;
import com.kodilla.library.repository.RentRepository;
import org.springframework.stereotype.Service;

@Service
public class RentService {

    private final RentRepository rentRepository;
    private final BookService bookService;

    public RentService(RentRepository rentRepository, BookService bookService) {
        this.rentRepository = rentRepository;
        this.bookService = bookService;
    }

    public Rent rentBook(Rent rent) throws InvalidStatusException {
        Book book = bookService.rentBook(rent.getBook());
        rent.setBook(book);
        return rentRepository.save(rent);
    }

    public Rent returnBook(Rent rent) throws InvalidStatusException {
        Book book = bookService.returnBook(rent.getBook());
        rent.setBook(book);
        return rentRepository.save(rent);
    }
}
