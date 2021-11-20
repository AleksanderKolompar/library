package com.kodilla.library.domain.dto;

import java.time.LocalDate;

public class RentResponse {

    private Long id;
    private BookResponse bookResponse;
    private ReaderResponse readerResponse;
    private LocalDate rentDate;
    private LocalDate returnDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookResponse getBookResponse() {
        return bookResponse;
    }

    public void setBookResponse(BookResponse bookResponse) {
        this.bookResponse = bookResponse;
    }

    public ReaderResponse getReaderResponse() {
        return readerResponse;
    }

    public void setReaderResponse(ReaderResponse readerResponse) {
        this.readerResponse = readerResponse;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
