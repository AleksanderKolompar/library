package com.kodilla.library.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Books")
public class Books {

    enum Status{
        RENTED, DESTROYED, LOST;
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
}
