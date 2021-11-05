package com.kodilla.library.mapper;

import com.kodilla.library.domain.Rents;
import com.kodilla.library.domain.dto.RentDto;
import org.springframework.stereotype.Service;

@Service
public class RentMapper {

    public RentDto mapToRentDto(Rents rents){
        RentDto rentDto = new RentDto();
        rentDto.setId(rents.getId());
        rentDto.setReaderId(rents.getReaderId());
        rentDto.setBookId(rents.getBookId());
        return rentDto;
    }

    public Rents mapToRents(RentDto rentDto){
        Rents rents = new Rents();
        rents.setBookId(rentDto.getBookId());
        rents.setReaderId(rentDto.getReaderId());
        return rents;
    }
}
