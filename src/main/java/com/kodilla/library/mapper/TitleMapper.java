package com.kodilla.library.mapper;

import com.kodilla.library.domain.Titles;
import com.kodilla.library.domain.dto.TitleDto;
import org.springframework.stereotype.Service;

@Service
public class TitleMapper {

    public TitleDto mapToTileDto(Titles titles){
        TitleDto titleDto = new TitleDto();
        titleDto.setId(titles.getId());
        titleDto.setTitle(titles.getTitle());
        titleDto.setAuthor(titles.getAuthor());
        titleDto.setYear(titles.getYear());
        titleDto.setBooksList(titles.getBooksList());
        return titleDto;
    }

    public Titles mapToTitles(TitleDto titleDto){
        Titles titles = new Titles();
        titles.setTitle(titles.getTitle());
        titles.setAuthor(titles.getAuthor());
        titles.setYear(titles.getYear());
        titles.setBooksList(titles.getBooksList());
        return titles;
    }
}
