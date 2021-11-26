package com.kodilla.library.domain;

import com.kodilla.library.controllers.exceptions.InvalidStatusException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "Books")
public class Book {

    public enum Status {
        AVAILABLE,
        RENTED,
        DESTROYED,
        LOST
    }

    @GeneratedValue
    @Id
    @NotNull
    @Column(name = "book_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "title_id")
    private Title title;

    @Column(name = "status")
    private Status status;

    protected Book() {
    }

    public Book(Title title, Status status) {
        this.title = title;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title titleId) {
        this.title = titleId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && status == book.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, status);
    }
}
