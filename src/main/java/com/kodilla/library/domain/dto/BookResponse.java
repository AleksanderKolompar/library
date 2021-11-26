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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookResponse)) return false;
        BookResponse that = (BookResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(titleResponse, that.titleResponse) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titleResponse, status);
    }
}
