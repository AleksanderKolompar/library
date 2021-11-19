package com.kodilla.library.domain;

import com.kodilla.library.controllers.exceptions.InvalidStatusException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

@Entity
@Table(name = "Books")
public class Book {

    public enum Status {
        AVAILABLE,
        RENTED,
        DESTROYED,
        LOST;

        public String getValue() {
            return this.toString();
        }
    }

    public Book() {
    }

    public Book(Title title) {
        this.title = title;
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
}
