package com.kodilla.library.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Readers")
public class Reader {

    public Reader() {
        this.rentList = new ArrayList<>();
    }

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

    @OneToMany(targetEntity = Rent.class,
            mappedBy = "reader")
    private List<Rent> rentList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Rent> getRentList() {
        return rentList;
    }

    public void setRentsList(List<Rent> rentList) {
        this.rentList = rentList;
    }
}
