package com.kodilla.library.mapper;

import com.kodilla.library.domain.Reader;
import com.kodilla.library.domain.dto.ReaderDto;
import org.springframework.stereotype.Service;

@Service
public class ReaderMapper {

    RentMapper rentMapper;


    public ReaderDto mapToReaderDto(Reader reader) {
        ReaderDto readerDto = new ReaderDto();
        readerDto.setId(reader.getId());
        readerDto.setFirstname(reader.getFirstname());
        readerDto.setLastname(reader.getLastname());
        readerDto.setRentDtosList(rentMapper.mapToRentDtoList(reader.getRentList()));
        return readerDto;
    }

    public Reader mapToReader(ReaderDto readerDto) {
        Reader reader = new Reader();
        reader.setFirstname(readerDto.getFirstname());
        reader.setLastname(readerDto.getLastname());
        reader.setRentsList(rentMapper.mapToRentsList(readerDto.getRentDtosList()));
        return reader;
    }
}
