package com.kodilla.library.domain.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

public class RentResponse {

    @NotNull
    private Long id;
    private BookResponse bookResponse;
    private ReaderResponse readerResponse;
    @NotNull
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentResponse that = (RentResponse) o;
        return id.equals(that.id) && Objects.equals(bookResponse, that.bookResponse) && Objects.equals(readerResponse, that.readerResponse) && rentDate.equals(that.rentDate) && Objects.equals(returnDate, that.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookResponse, readerResponse, rentDate, returnDate);
    }
}
