package com.kodilla.library.domain.dto;

import com.kodilla.library.domain.Book;

import javax.validation.constraints.NotNull;

public class BookUpdateRequest {

    @NotNull
    private Long id;
    private Book.Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book.Status getStatus() {
        return status;
    }

    public void setStatus(Book.Status status) {
        this.status = status;
    }
}
