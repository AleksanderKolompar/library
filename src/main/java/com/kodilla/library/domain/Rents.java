package com.kodilla.library.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "Rents")
public class Rents {

    public Rents() {
    }

    @GeneratedValue
    @Id
    @NotNull
    @Column(name = "rent_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Books bookId;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private Readers readerId;
//
//    @Column(name = "rent_date")
//    private LocalDate rentDate;
//
//    @Column(name = "return_date")
//    private LocalDate returnDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Books getBookId() {
        return bookId;
    }

    public void setBookId(Books bookId) {
        this.bookId = bookId;
    }

    public Readers getReaderId() {
        return readerId;
    }

    public void setReaderId(Readers readerId) {
        this.readerId = readerId;
    }
}
