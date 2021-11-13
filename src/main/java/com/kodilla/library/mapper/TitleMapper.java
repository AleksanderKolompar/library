package com.kodilla.library.mapper;

import com.kodilla.library.domain.Title;
import com.kodilla.library.domain.dto.TitleRequest;
import com.kodilla.library.domain.dto.TitleResponse;
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

    public Title mapToTitle(TitleRequest titleRequest) {
        Title title = new Title();
        title.setTitle(titleRequest.getTitle());
        title.setAuthor(titleRequest.getAuthor());
        title.setYear(titleRequest.getYear());
        return title;
    }

    public List<TitleResponse> mapToTitleResponseList(List<Title> titles) {
        return titles.stream()
                .map(this::mapToTitleResponse)
                .collect(Collectors.toList());
    }
}
