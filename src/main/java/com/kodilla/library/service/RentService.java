package com.kodilla.library.service;

import com.kodilla.library.controllers.exceptions.InvalidStatusException;
import com.kodilla.library.controllers.exceptions.RentNotFoundException;
import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.Rent;
import com.kodilla.library.repository.RentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentService {

    private final RentRepository rentRepository;
    private final BookService bookService;

    public RentService(RentRepository rentRepository, BookService bookService) {
        this.rentRepository = rentRepository;
        this.bookService = bookService;
    }

    public Rent get(Long id) throws RentNotFoundException {
        return rentRepository.findById(id).orElseThrow(RentNotFoundException::new);
    }

    public List<Rent> get() {
        return rentRepository.findAll();
    }

    public Rent rentBook(Rent rent) throws InvalidStatusException {
        Book book = bookService.rentBook(rent.getBook());
        rent.setBook(book);
        rent.setRentDate(LocalDate.now());
        return rentRepository.save(rent);
    }

    public Rent returnBook(Rent rent) throws InvalidStatusException {
        Book book = bookService.returnBook(rent.getBook());
        rent.setBook(book);
        rent.setReturnDate(LocalDate.now());
        rent.getBook().setStatus(Book.Status.AVAILABLE);
        return rentRepository.save(rent);
    }
}
