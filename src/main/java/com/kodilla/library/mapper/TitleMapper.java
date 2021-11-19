package com.kodilla.library.mapper;

import com.kodilla.library.domain.Title;
import com.kodilla.library.domain.dto.TitleResponse;
import com.kodilla.library.domain.dto.TitleSaveRequest;
import com.kodilla.library.domain.dto.TitleUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TitleMapper {

    public TitleResponse mapToTitleResponse(Title title) {
        TitleResponse titleResponse = new TitleResponse();
        titleResponse.setId(title.getId());
        titleResponse.setTitle(title.getTitle());
        titleResponse.setAuthor(title.getAuthor());
        titleResponse.setYear(title.getYear());
        return titleResponse;
    }

    public Title mapToTitle(TitleSaveRequest titleSaveRequest) {
        Title title = new Title();
        title.setTitle(titleSaveRequest.getTitle());
        title.setAuthor(titleSaveRequest.getAuthor());
        title.setYear(titleSaveRequest.getYear());
        return title;
    }

    public Title mapToTitle(TitleUpdateRequest titleUpdateRequest) {
        Title title = new Title();
        title.setId(titleUpdateRequest.getId());
        title.setTitle(titleUpdateRequest.getTitle());
        title.setAuthor(titleUpdateRequest.getAuthor());
        title.setYear(titleUpdateRequest.getYear());
        return title;
    }

    public List<TitleResponse> mapToTitleResponseList(List<Title> titles) {
        return titles.stream()
                .map(this::mapToTitleResponse)
                .collect(Collectors.toList());
    }
}
