package com.kodilla.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ReaderDto {

    public ReaderDto() {
        this.rentDtosList = new ArrayList<>();
    }

    private Long id;
    private String firstname;
    private String lastname;
    private List<RentDto> rentDtosList;
}