package com.kodilla.library.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Readers")
public class Readers {

    @GeneratedValue
    @Id
    @NotNull
    @Column(name = "reader_id")
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;
//
//    @Column(name = "registered")
//    private LocalDate date;

    @OneToMany(targetEntity = Rents.class,
            mappedBy = "readerId")
    private List<Rents> rentsList;
}
