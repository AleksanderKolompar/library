package com.kodilla.library.mapper;

import com.kodilla.library.domain.Rent;
import com.kodilla.library.domain.dto.RentDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentMapper {

    BookMapper bookMapper;
    ReaderMapper readerMapper;

    public RentDto mapToRentDto(Rent rent) {
        RentDto rentDto = new RentDto();
        rentDto.setId(rent.getId());
        rentDto.setReaderDto(readerMapper.mapToReaderDto(rent.getReader()));
        rentDto.setBookDto(bookMapper.mapToBookDto(rent.getBook()));
        return rentDto;
    }

    public Rent mapToRent(RentDto rentDto) {
        Rent rent = new Rent();
        rent.setBook(bookMapper.mapToBook(rentDto.getBookDto()));
        rent.setReader(readerMapper.mapToReader(rentDto.getReaderDto()));
        return rent;
    }

    public List<RentDto> mapToRentDtoList(List<Rent> rentList) {
        return rentList.stream().map(this::mapToRentDto).collect(Collectors.toList());
    }

    public List<Rent> mapToRentsList(List<RentDto> rentDtoList) {
        return rentDtoList.stream().map(this::mapToRent).collect(Collectors.toList());
    }
}
