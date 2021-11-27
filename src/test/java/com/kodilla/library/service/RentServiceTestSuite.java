package com.kodilla.library.service;

import com.kodilla.library.controllers.exceptions.BookNotFoundException;
import com.kodilla.library.controllers.exceptions.InvalidStatusException;
import com.kodilla.library.controllers.exceptions.ReaderNotFoundException;
import com.kodilla.library.controllers.exceptions.RentNotFoundException;
import com.kodilla.library.domain.Rent;
import com.kodilla.library.domain.dto.*;
import com.kodilla.library.repository.BookRepository;
import com.kodilla.library.repository.ReaderRepository;
import com.kodilla.library.repository.RentRepository;
import com.kodilla.library.repository.TitleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RentServiceTestSuite {

    @Autowired
    private RentService rentService;
    @Autowired
    private BookService bookService;
    @Autowired
    private TitleService titleService;
    @Autowired
    private ReaderService readerService;
    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private TitleRepository titleRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReaderRepository readerRepository;


    @Test
    void shouldRent() {
        //Given
        ReaderSaveRequest readerSaveRequest = new ReaderSaveRequest("String", "String");
        ReaderResponse readerResponse = readerService.save(readerSaveRequest);

        TitleSaveRequest titleSaveRequest = new TitleSaveRequest("String", "String", 0);
        TitleResponse titleResponse = titleService.save(titleSaveRequest);

        BookSaveRequest bookSaveRequest = new BookSaveRequest(titleResponse.getId());
        BookResponse bookResponse = bookService.save(bookSaveRequest);

        RentRequest rentRequest = new RentRequest(bookResponse.getId(), readerResponse.getId());
        //When
        RentResponse rentResponse = rentService.rentBook(rentRequest);
        BookResponse expectedBookResponse = bookService.get(bookResponse.getId());
        //Then
        assertEquals(expectedBookResponse, rentResponse.getBookResponse());
        assertEquals(readerResponse, rentResponse.getReaderResponse());
        assertNotEquals(rentResponse.getRentDate(), null);
        assertNull(rentResponse.getReturnDate());
        //CleanUp
        try {
            rentRepository.deleteById(rentResponse.getId());
            readerService.delete(readerResponse.getId());
            bookRepository.deleteById(expectedBookResponse.getId());
            titleRepository.deleteById(expectedBookResponse.getTitleResponse().getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    void shouldNotRentTwice() {
        //Given
        ReaderSaveRequest readerSaveRequest = new ReaderSaveRequest("String", "String");
        ReaderResponse readerResponse = readerService.save(readerSaveRequest);

        TitleSaveRequest titleSaveRequest = new TitleSaveRequest("String", "String", 0);
        TitleResponse titleResponse = titleService.save(titleSaveRequest);

        BookSaveRequest bookSaveRequest = new BookSaveRequest(titleResponse.getId());
        BookResponse bookResponse = bookService.save(bookSaveRequest);

        RentRequest rentRequest = new RentRequest(bookResponse.getId(), readerResponse.getId());
        RentResponse rentResponse = rentService.rentBook(rentRequest);
        //When & Then
        assertThrows(InvalidStatusException.class, () -> rentService.rentBook(rentRequest));
        //CleanUp
        try {
            rentRepository.deleteById(rentResponse.getId());
            readerService.delete(readerResponse.getId());
            bookRepository.deleteById(bookResponse.getId());
            titleRepository.deleteById(titleResponse.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    void shouldNotRentDeletedBook() {
        //Given
        ReaderSaveRequest readerSaveRequest = new ReaderSaveRequest("String", "String");
        ReaderResponse readerResponse = readerService.save(readerSaveRequest);

        TitleSaveRequest titleSaveRequest = new TitleSaveRequest("String", "String", 0);
        TitleResponse titleResponse = titleService.save(titleSaveRequest);

        BookSaveRequest bookSaveRequest = new BookSaveRequest(titleResponse.getId());
        BookResponse bookResponse = bookService.save(bookSaveRequest);

        RentRequest rentRequest = new RentRequest(bookResponse.getId(), readerResponse.getId());

        bookResponse = bookService.delete(bookResponse.getId());
        //When & Then
        assertThrows(InvalidStatusException.class, () -> rentService.rentBook(rentRequest));
        //CleanUp
        try {
            readerService.delete(readerResponse.getId());
            bookRepository.deleteById(bookResponse.getId());
            titleRepository.deleteById(titleResponse.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    void shouldNotRentForDeletedReader() {
        //Given
        ReaderSaveRequest readerSaveRequest = new ReaderSaveRequest("String", "String");
        ReaderResponse readerResponse = readerService.save(readerSaveRequest);

        TitleSaveRequest titleSaveRequest = new TitleSaveRequest("String", "String", 0);
        TitleResponse titleResponse = titleService.save(titleSaveRequest);

        BookSaveRequest bookSaveRequest = new BookSaveRequest(titleResponse.getId());
        BookResponse bookResponse = bookService.save(bookSaveRequest);

        RentRequest rentRequest = new RentRequest(bookResponse.getId(), readerResponse.getId());

        readerService.delete(readerResponse.getId());
        //When & Then
        assertThrows(ReaderNotFoundException.class, () -> rentService.rentBook(rentRequest));
        //CleanUp
        try {
            readerService.delete(readerResponse.getId());
            bookRepository.deleteById(bookResponse.getId());
            titleRepository.deleteById(titleResponse.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    void shouldGetOneRent() {
        //Given
        ReaderSaveRequest readerSaveRequest = new ReaderSaveRequest("String", "String");
        ReaderResponse readerResponse = readerService.save(readerSaveRequest);

        TitleSaveRequest titleSaveRequest = new TitleSaveRequest("String", "String", 0);
        TitleResponse titleResponse = titleService.save(titleSaveRequest);

        BookSaveRequest bookSaveRequest = new BookSaveRequest(titleResponse.getId());
        BookResponse bookResponse1 = bookService.save(bookSaveRequest);
        BookResponse bookResponse2 = bookService.save(bookSaveRequest);

        RentRequest rentRequest1 = new RentRequest(bookResponse1.getId(), readerResponse.getId());
        RentRequest rentRequest2 = new RentRequest(bookResponse2.getId(), readerResponse.getId());

        RentResponse expectedRentResponse = rentService.rentBook(rentRequest1);
        RentResponse unexpectedRentResponse = rentService.rentBook(rentRequest2);
        //When
        RentResponse actualRentResponse = rentService.get(expectedRentResponse.getId());
        //Then
        assertEquals(expectedRentResponse, actualRentResponse);
        assertNotEquals(unexpectedRentResponse, actualRentResponse);
        //CleanUp
        try {
            rentRepository.deleteById(expectedRentResponse.getId());
            rentRepository.deleteById(unexpectedRentResponse.getId());
            readerService.delete(readerResponse.getId());
            bookRepository.deleteById(bookResponse1.getId());
            bookRepository.deleteById(bookResponse2.getId());
            titleRepository.deleteById(titleResponse.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    void shouldGetAllRents() {
        //Given
        ReaderSaveRequest readerSaveRequest = new ReaderSaveRequest("String", "String");
        ReaderResponse readerResponse = readerService.save(readerSaveRequest);

        TitleSaveRequest titleSaveRequest = new TitleSaveRequest("String", "String", 0);
        TitleResponse titleResponse = titleService.save(titleSaveRequest);

        BookSaveRequest bookSaveRequest = new BookSaveRequest(titleResponse.getId());
        BookResponse bookResponse1 = bookService.save(bookSaveRequest);
        BookResponse bookResponse2 = bookService.save(bookSaveRequest);

        RentRequest rentRequest1 = new RentRequest(bookResponse1.getId(), readerResponse.getId());
        RentRequest rentRequest2 = new RentRequest(bookResponse2.getId(), readerResponse.getId());

        RentResponse rentResponse1 = rentService.rentBook(rentRequest1);
        RentResponse rentResponse2 = rentService.rentBook(rentRequest2);
        //When
        List<RentResponse> rentResponseList = rentService.get();
        //Then
        assertEquals(2, rentResponseList.size());
        assertTrue(rentResponseList.contains(rentResponse1));
        assertTrue(rentResponseList.contains(rentResponse2));
        //CleanUp
        try {
            rentRepository.deleteById(rentResponse1.getId());
            rentRepository.deleteById(rentResponse2.getId());
            bookRepository.deleteById(bookResponse1.getId());
            bookRepository.deleteById(bookResponse2.getId());
            titleRepository.deleteById(titleResponse.getId());
            readerService.delete(readerResponse.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    void shouldReturn() {
        //Given
        ReaderSaveRequest readerSaveRequest = new ReaderSaveRequest("String", "String");
        ReaderResponse readerResponse = readerService.save(readerSaveRequest);

        TitleSaveRequest titleSaveRequest = new TitleSaveRequest("String", "String", 0);
        TitleResponse titleResponse = titleService.save(titleSaveRequest);

        BookSaveRequest bookSaveRequest = new BookSaveRequest(titleResponse.getId());
        BookResponse bookResponse = bookService.save(bookSaveRequest);

        RentRequest rentRequest = new RentRequest(bookResponse.getId(), readerResponse.getId());
        RentResponse rentResponse = rentService.rentBook(rentRequest);
        //When
        RentResponse returnResponse = rentService.returnBook(rentResponse.getId());
        // Then
        assertEquals(rentResponse.getId(), returnResponse.getId());
        assertEquals(rentResponse.getBookResponse().getId(), returnResponse.getBookResponse().getId());
        assertEquals(rentResponse.getBookResponse().getTitleResponse(), returnResponse.getBookResponse().getTitleResponse());
        assertNotEquals(rentResponse.getBookResponse().getStatus(), returnResponse.getBookResponse().getStatus());
        assertEquals(rentResponse.getReaderResponse(), returnResponse.getReaderResponse());
        assertEquals(rentResponse.getRentDate(), returnResponse.getRentDate());
        assertEquals(rentResponse.getReaderResponse(), returnResponse.getReaderResponse());
        assertNotEquals(rentResponse.getReturnDate(), returnResponse.getReturnDate());
        //CleanUp
        try {
            rentRepository.deleteById(returnResponse.getId());
            readerService.delete(readerResponse.getId());
            bookRepository.deleteById(bookResponse.getId());
            titleRepository.deleteById(titleResponse.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    void shouldNotReturnTwice() {
        //Given
        ReaderSaveRequest readerSaveRequest = new ReaderSaveRequest("String", "String");
        ReaderResponse readerResponse = readerService.save(readerSaveRequest);

        TitleSaveRequest titleSaveRequest = new TitleSaveRequest("String", "String", 0);
        TitleResponse titleResponse = titleService.save(titleSaveRequest);

        BookSaveRequest bookSaveRequest = new BookSaveRequest(titleResponse.getId());
        BookResponse bookResponse = bookService.save(bookSaveRequest);

        RentRequest rentRequest = new RentRequest(bookResponse.getId(), readerResponse.getId());
        RentResponse rentResponse = rentService.rentBook(rentRequest);
        RentResponse returnResponse = rentService.returnBook(rentResponse.getId());
        //When & Then
        assertThrows(InvalidStatusException.class, () -> rentService.returnBook(rentResponse.getId()));
        //CleanUp
        try {
            rentRepository.deleteById(returnResponse.getId());
            readerService.delete(readerResponse.getId());
            bookRepository.deleteById(bookResponse.getId());
            titleRepository.deleteById(titleResponse.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    void shouldNotReturnDeletedBook() {
        //Given
        ReaderSaveRequest readerSaveRequest = new ReaderSaveRequest("String", "String");
        ReaderResponse readerResponse = readerService.save(readerSaveRequest);

        TitleSaveRequest titleSaveRequest = new TitleSaveRequest("String", "String", 0);
        TitleResponse titleResponse = titleService.save(titleSaveRequest);

        BookSaveRequest bookSaveRequest = new BookSaveRequest(titleResponse.getId());
        BookResponse bookResponse = bookService.save(bookSaveRequest);

        RentRequest rentRequest = new RentRequest(bookResponse.getId(), readerResponse.getId());
        RentResponse rentResponse = rentService.rentBook(rentRequest);
        bookResponse = bookService.delete(bookResponse.getId());
        //When & Then
        assertThrows(InvalidStatusException.class, () -> rentService.returnBook(rentResponse.getId()));
        //CleanUp
        try {
            rentRepository.deleteById(rentResponse.getId());
            readerService.delete(readerResponse.getId());
            bookRepository.deleteById(bookResponse.getId());
            titleRepository.deleteById(titleResponse.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    void shouldNotReturnDeletedRentRecord() {
        //Given
        ReaderSaveRequest readerSaveRequest = new ReaderSaveRequest("String", "String");
        ReaderResponse readerResponse = readerService.save(readerSaveRequest);

        TitleSaveRequest titleSaveRequest = new TitleSaveRequest("String", "String", 0);
        TitleResponse titleResponse = titleService.save(titleSaveRequest);

        BookSaveRequest bookSaveRequest = new BookSaveRequest(titleResponse.getId());
        BookResponse bookResponse = bookService.save(bookSaveRequest);

        RentRequest rentRequest = new RentRequest(bookResponse.getId(), readerResponse.getId());
        RentResponse rentResponse = rentService.rentBook(rentRequest);
        rentRepository.deleteById(rentResponse.getId());
        //When & Then
        assertThrows(RentNotFoundException.class, () -> rentService.returnBook(rentResponse.getId()));
        //CleanUp
        try {
            readerService.delete(readerResponse.getId());
            bookRepository.deleteById(bookResponse.getId());
            titleRepository.deleteById(titleResponse.getId());
        } catch (Exception e) {
            //do nothing
        }
    }
}
