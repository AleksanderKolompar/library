package com.kodilla.library.service;

import com.kodilla.library.controllers.exceptions.BookNotFoundException;
import com.kodilla.library.controllers.exceptions.InvalidStatusException;
import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.dto.*;
import com.kodilla.library.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTestSuite {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TitleService titleService;


    @Test
    void shouldSaveBook() {
        //Given
        TitleSaveRequest titleSaveRequest = new TitleSaveRequest("String", "String", 0);
        TitleResponse titleResponse = titleService.save(titleSaveRequest);
        BookSaveRequest bookSaveRequest = new BookSaveRequest();
        bookSaveRequest.setTitleId(titleResponse.getId());

        BookResponse expectedResponse = new BookResponse();
        expectedResponse.setId(titleResponse.getId() + 1);
        expectedResponse.setTitleResponse(titleResponse);
        expectedResponse.setStatus(Book.Status.AVAILABLE);
        //When
        BookResponse bookResponse = bookService.save(bookSaveRequest);
        //Then
        assertEquals(expectedResponse.getId(), bookResponse.getId());
        //CleanUp
        try {
            titleService.delete(titleResponse.getId());
            bookService.delete(bookResponse.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    void shouldGetAllBooks() {
        //Given
        TitleSaveRequest titleSaveRequest = new TitleSaveRequest("String", "String", 0);
        TitleResponse titleResponse = titleService.save(titleSaveRequest);

        BookSaveRequest bookSaveRequest = new BookSaveRequest();
        bookSaveRequest.setTitleId(titleResponse.getId());

        BookResponse bookResponse1 = bookService.save(bookSaveRequest);
        BookResponse bookResponse2 = bookService.save(bookSaveRequest);
        BookResponse bookResponse3 = bookService.save(bookSaveRequest);
        //When
        List<BookResponse> bookResponseList = bookService.get();
        //Then
        assertEquals(3, bookResponseList.size());
        //CleanUp
        try {
            titleService.delete(titleResponse.getId());
            bookService.delete(bookResponse1.getId());
            bookService.delete(bookResponse2.getId());
            bookService.delete(bookResponse3.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    void shouldGetOneBook() {
        //Given
        TitleSaveRequest titleSaveRequest = new TitleSaveRequest("String", "String", 0);
        TitleResponse titleResponse = titleService.save(titleSaveRequest);

        BookSaveRequest bookSaveRequest = new BookSaveRequest();
        bookSaveRequest.setTitleId(titleResponse.getId());

        BookResponse bookResponse1 = bookService.save(bookSaveRequest);
        BookResponse bookResponse2 = bookService.save(bookSaveRequest);
        BookResponse bookResponse3 = bookService.save(bookSaveRequest);
        //When
        BookResponse expectedBook = bookService.get(bookResponse2.getId());
        //Then
        assertEquals(bookResponse2, expectedBook);
        //CleanUp
        try {
            titleService.delete(titleResponse.getId());
            bookService.delete(bookResponse1.getId());
            bookService.delete(bookResponse2.getId());
            bookService.delete(bookResponse3.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    void shouldDeleteBook() {
        //Given
        TitleSaveRequest titleSaveRequest = new TitleSaveRequest("String", "String", 0);
        TitleResponse titleResponse = titleService.save(titleSaveRequest);

        BookSaveRequest bookSaveRequest = new BookSaveRequest();
        bookSaveRequest.setTitleId(titleResponse.getId());

        BookResponse bookResponse1 = bookService.save(bookSaveRequest);
        BookResponse bookResponse2 = bookService.save(bookSaveRequest);
        BookResponse bookResponse3 = bookService.save(bookSaveRequest);
        //When
        bookService.delete(bookResponse2.getId());
        //Then
        assertFalse(bookRepository.existsById(bookResponse2.getId()));
        //CleanUp
        try {
            titleService.delete(titleResponse.getId());
            bookService.delete(bookResponse1.getId());
            bookService.delete(bookResponse3.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    void shouldRentBook() {
        //Given
        TitleSaveRequest titleSaveRequest = new TitleSaveRequest("String", "String", 0);
        TitleResponse titleResponse = titleService.save(titleSaveRequest);

        BookSaveRequest bookSaveRequest = new BookSaveRequest();
        bookSaveRequest.setTitleId(titleResponse.getId());

        BookResponse bookResponse = bookService.save(bookSaveRequest);
        //When
        Book expectedBook = bookService.rentBook(bookResponse.getId());
        //Then
        assertEquals(Book.Status.RENTED, expectedBook.getStatus());
        //CleanUp
        try {
            titleService.delete(titleResponse.getId());
            bookService.delete(bookResponse.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    void shouldNotRentBookTwice() {
        //Given
        TitleSaveRequest titleSaveRequest = new TitleSaveRequest("String", "String", 0);
        TitleResponse titleResponse = titleService.save(titleSaveRequest);

        BookSaveRequest bookSaveRequest = new BookSaveRequest();
        bookSaveRequest.setTitleId(titleResponse.getId());

        BookResponse bookResponse = bookService.save(bookSaveRequest);
        bookService.rentBook(bookResponse.getId());
        //When and Then
        assertThrows(InvalidStatusException.class, () -> bookService.rentBook(bookResponse.getId()));
        //CleanUp
        try {
            titleService.delete(titleResponse.getId());
            bookService.delete(bookResponse.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    void shouldNotRentDeletedBook() {
        //Given
        TitleSaveRequest titleSaveRequest = new TitleSaveRequest("String", "String", 0);
        TitleResponse titleResponse = titleService.save(titleSaveRequest);

        BookSaveRequest bookSaveRequest = new BookSaveRequest();
        bookSaveRequest.setTitleId(titleResponse.getId());

        BookResponse bookResponse = bookService.save(bookSaveRequest);
        bookService.delete(bookResponse.getId());
        //When and Then
        assertThrows(BookNotFoundException.class, () -> bookService.rentBook(bookResponse.getId()));
        //CleanUp
        try {
            titleService.delete(titleResponse.getId());
            bookService.delete(bookResponse.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    void shouldReturnBook() {
        //Given
        TitleSaveRequest titleSaveRequest = new TitleSaveRequest("String", "String", 0);
        TitleResponse titleResponse = titleService.save(titleSaveRequest);

        BookSaveRequest bookSaveRequest = new BookSaveRequest();
        bookSaveRequest.setTitleId(titleResponse.getId());

        BookResponse bookResponse = bookService.save(bookSaveRequest);
        bookService.rentBook(bookResponse.getId());
        //When
        Book expectedBook = bookService.returnBook(bookResponse.getId());
        //Then
        assertEquals(Book.Status.AVAILABLE, expectedBook.getStatus());
        //CleanUp
        try {
            titleService.delete(titleResponse.getId());
            bookService.delete(bookResponse.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    void shouldNotReturnAvailableBook() {
        //Given
        TitleSaveRequest titleSaveRequest = new TitleSaveRequest("String", "String", 0);
        TitleResponse titleResponse = titleService.save(titleSaveRequest);

        BookSaveRequest bookSaveRequest = new BookSaveRequest();
        bookSaveRequest.setTitleId(titleResponse.getId());

        BookResponse bookResponse = bookService.save(bookSaveRequest);
        //When and Then
        assertThrows(InvalidStatusException.class, () -> bookService.returnBook(bookResponse.getId()));
        //CleanUp
        try {
            titleService.delete(titleResponse.getId());
            bookService.delete(bookResponse.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    void shouldNotReturnDeletedBook() {
        //Given
        TitleSaveRequest titleSaveRequest = new TitleSaveRequest("String", "String", 0);
        TitleResponse titleResponse = titleService.save(titleSaveRequest);

        BookSaveRequest bookSaveRequest = new BookSaveRequest();
        bookSaveRequest.setTitleId(titleResponse.getId());

        BookResponse bookResponse = bookService.save(bookSaveRequest);
        bookService.delete(bookResponse.getId());
        //When and Then
        assertThrows(BookNotFoundException.class, () -> bookService.returnBook(bookResponse.getId()));
        //CleanUp
        try {
            titleService.delete(titleResponse.getId());
            bookService.delete(bookResponse.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    void shouldFindAvailable() {
        //Given
        TitleSaveRequest titleSaveRequest = new TitleSaveRequest("String", "String", 0);
        TitleResponse titleResponse1 = titleService.save(titleSaveRequest);
        TitleResponse titleResponse2 = titleService.save(titleSaveRequest);

        BookSaveRequest bookSaveRequest1 = new BookSaveRequest();
        BookSaveRequest bookSaveRequest2 = new BookSaveRequest();
        bookSaveRequest1.setTitleId(titleResponse1.getId());
        bookSaveRequest2.setTitleId(titleResponse2.getId());

        BookResponse bookResponse1 = bookService.save(bookSaveRequest1);
        BookResponse bookResponse2 = bookService.save(bookSaveRequest2);
        BookResponse bookResponse3 = bookService.save(bookSaveRequest2);

        bookService.rentBook(bookResponse2.getId());
        //When
        List<BookResponse> expectedBooks = bookService.findBookWithStatus(titleResponse2.getId(), Book.Status.AVAILABLE);
        //Then
        assertEquals(1, expectedBooks.size());
        assertEquals(bookResponse3, expectedBooks.get(0));
        //CleanUp
        try {
            titleService.delete(titleResponse1.getId());
            titleService.delete(titleResponse2.getId());
            bookService.delete(bookResponse1.getId());
            bookService.delete(bookResponse2.getId());
            bookService.delete(bookResponse3.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    void shouldChangeStatus() {
        //Given
        TitleSaveRequest titleSaveRequest = new TitleSaveRequest("String", "String", 0);
        TitleResponse titleResponse = titleService.save(titleSaveRequest);

        BookSaveRequest bookSaveRequest = new BookSaveRequest();
        bookSaveRequest.setTitleId(titleResponse.getId());

        BookResponse bookResponse = bookService.save(bookSaveRequest);
        //When
        BookResponse expectedBookResponse = bookService.markBook(new BookUpdateRequest(bookResponse.getId(), Book.Status.DESTROYED));
        //Then
        assertEquals(Book.Status.DESTROYED, expectedBookResponse.getStatus());
        //CleanUp
        try {
            titleService.delete(titleResponse.getId());
            bookService.delete(bookResponse.getId());
        } catch (Exception e) {
            //do nothing
        }
    }
}