package com.kodilla.library.mapper;

import com.kodilla.library.domain.Title;
import com.kodilla.library.domain.dto.TitleDto;
import org.springframework.stereotype.Service;

@Service
public class TitleMapper {

    public TitleDto mapToTileDto(Title title) {
        TitleDto titleDto = new TitleDto();
        titleDto.setId(title.getId());
        titleDto.setTitle(title.getTitle());
        titleDto.setAuthor(title.getAuthor());
        titleDto.setYear(title.getYear());
        titleDto.setBookList(title.getBooksList());
        return titleDto;
    }

    public Title mapToTitles(TitleDto titleDto) {
        Title title = new Title();
        title.setTitle(title.getTitle());
        title.setAuthor(title.getAuthor());
        title.setYear(title.getYear());
        title.setBooksList(title.getBooksList());
        return title;
    }
}
