package com.kodilla.library.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "Rents")
public class Rents {

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


}
