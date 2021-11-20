package com.kodilla.library.domain.dto;

import com.kodilla.library.domain.Book;

public class BookResponse {

    private Long id;
    private TitleResponse titleResponse;
    private Book.Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TitleResponse getTitleResponse() {
        return titleResponse;
    }

    public void setTitleResponse(TitleResponse titleResponse) {
        this.titleResponse = titleResponse;
    }

    public Book.Status getStatus() {
        return status;
    }

    public void setStatus(Book.Status status) {
        this.status = status;
    }
}
