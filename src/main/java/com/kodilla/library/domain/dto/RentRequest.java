package com.kodilla.library.domain.dto;

import javax.validation.constraints.NotNull;

public class RentRequest {

    @NotNull
    private Long bookId;
    @NotNull
    private Long readerId;

    public RentRequest(Long bookId, Long readerId) {
        this.bookId = bookId;
        this.readerId = readerId;
    }

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
