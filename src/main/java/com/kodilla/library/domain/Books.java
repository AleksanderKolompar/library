package com.kodilla.library.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Books")
public class Books {

    public static enum Status{
        RENTED, DESTROYED, LOST;
    }

    public Books() {
    }

    @GeneratedValue
    @Id
    @NotNull
    @Column(name = "book_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "title_id")
    private Titles titleId;

    @Column(name = "status")
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Titles getTitleId() {
        return titleId;
    }

    public void setTitleId(Titles titleId) {
        this.titleId = titleId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
