package com.kodilla.library.domain.dto;

import com.kodilla.library.domain.Rents;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReaderDto {

    public ReaderDto() {
        this.rentsList = new ArrayList<>();
    }

    private Long id;
    private String firstname;
    private String lastname;
    private List<Rents> rentsList;
}
