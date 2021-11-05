package com.kodilla.library.mapper;

import com.kodilla.library.domain.Readers;
import com.kodilla.library.domain.dto.ReaderDto;
import org.springframework.stereotype.Service;

@Service
public class ReaderMapper {
     public ReaderDto mapToReaderDto(Readers readers){
        ReaderDto readerDto = new ReaderDto();
        readerDto.setId(readers.getId());
        readerDto.setFirstname(readers.getFirstname());
        readerDto.setLastname(readers.getLastname());
        readerDto.setRentsList(readers.getRentsList());
        return readerDto;
     }

     public Readers mapToReaders(ReaderDto readerDto){
         Readers readers = new Readers();
         readers.setFirstname(readerDto.getFirstname());
         readers.setLastname(readerDto.getLastname());
         readers.setRentsList(readerDto.getRentsList());
         return readers;
     }
}
