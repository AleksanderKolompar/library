package com.kodilla.library.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Readers")
public class Reader {

    @GeneratedValue
    @Id
    @NotNull
    @Column(name = "reader_id")
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "registered")
    private LocalDate registrationDate;

    @OneToMany(targetEntity = Rent.class,
            mappedBy = "reader")
    private List<Rent> rentList = new ArrayList<>();

    protected Reader() {
    }

    public Reader(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Reader(Long id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

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

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registered) {
        this.registrationDate = registered;
    }
}
