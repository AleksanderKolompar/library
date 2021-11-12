package com.kodilla.library.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Rents")
public class Rent {

    public Rent() {
    }

    @GeneratedValue
    @Id
    @NotNull
    @Column(name = "rent_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private Reader reader;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book bookId) {
        this.book = bookId;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader readerId) {
        this.reader = readerId;
    }
}
