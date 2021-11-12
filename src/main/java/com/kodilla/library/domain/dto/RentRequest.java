package com.kodilla.library.domain.dto;

public class RentRequest {

    public RentRequest() {
    }

    public RentRequest(Long bookId, Long readerId) {
        this.bookId = bookId;
        this.readerId = readerId;
    }

    private Long bookId;
    private Long readerId;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }
}
