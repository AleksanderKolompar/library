package com.kodilla.library.domain;

import com.kodilla.library.controllers.exceptions.InvalidStatusException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

@Entity
@Table(name = "Books")
public class Book {

    public enum Status {
        AVAILABLE("available"),
        RENTED("rented"),
        DESTROYED("destroyed"),
        LOST("lost");

        private String value;

        private Status(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public Status getStatus(String value) throws InvalidStatusException {
            return Arrays.stream(Status.values())
                    .filter(status -> status.getValue().equals(value)).findAny().orElseThrow(InvalidStatusException::new);
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
