package com.kodilla.library.service;


import com.kodilla.library.controllers.exceptions.TitleNotFoundException;
import com.kodilla.library.domain.Title;
import com.kodilla.library.domain.dto.TitleResponse;
import com.kodilla.library.domain.dto.TitleSaveRequest;
import com.kodilla.library.domain.dto.TitleUpdateRequest;
import com.kodilla.library.repository.TitleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TitleServiceTestSuite {

    @Autowired
    private TitleService titleService;

    @Autowired
    private TitleRepository titleRepository;

    @Test
    void shouldSaveTitle(){
        //Given
        TitleSaveRequest titleSaveRequest = new TitleSaveRequest("String", "String", 0);
        //When
        TitleResponse titleResponse = titleService.save(titleSaveRequest);
        //Then
        assertEquals(titleSaveRequest.getTitle(), titleResponse.getTitle());
        assertEquals(titleSaveRequest.getAuthor(), titleResponse.getAuthor());
        assertEquals(titleSaveRequest.getYear(), titleResponse.getYear());
        try {
            titleService.delete(titleResponse.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    void shouldGetAllTitles(){
        titleRepository.deleteAll();
        //Given
        TitleSaveRequest titleSaveRequest = new TitleSaveRequest("String", "String", 0);
        TitleResponse titleResponse1 = titleService.save(titleSaveRequest);
        TitleResponse titleResponse2 = titleService.save(titleSaveRequest);
        TitleResponse titleResponse3 = titleService.save(titleSaveRequest);
        //When
        List<TitleResponse> titleResponseList = titleService.get();
        //Then
        assertEquals(3, titleResponseList.size());
        assertTrue(titleResponseList.contains(titleResponse1));
        assertTrue(titleResponseList.contains(titleResponse2));
        assertTrue(titleResponseList.contains(titleResponse3));
        try {
            titleService.delete(titleResponse1.getId());
            titleService.delete(titleResponse2.getId());
            titleService.delete(titleResponse3.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    void shouldGetOneTitle(){
        //Given
        TitleSaveRequest titleSaveRequest = new TitleSaveRequest("String", "String", 0);
        TitleResponse titleResponse1 = titleService.save(titleSaveRequest);
        TitleResponse titleResponse2 = titleService.save(titleSaveRequest);
        //When
        TitleResponse titleResponse = titleService.get(titleResponse1.getId());
        //Then
        assertEquals(titleResponse1, titleResponse);
        assertNotEquals(titleResponse2, titleResponse);
        try {
            titleService.delete(titleResponse1.getId());
            titleService.delete(titleResponse2.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    void shouldDeleteTitle(){
        //Given
        TitleSaveRequest titleSaveRequest = new TitleSaveRequest("String", "String", 0);
        TitleResponse titleResponse1 = titleService.save(titleSaveRequest);
        TitleResponse titleResponse2 = titleService.save(titleSaveRequest);
        //When
        titleService.delete(titleResponse1.getId());
        //Then
        assertFalse(titleRepository.existsById(titleResponse1.getId()));
        assertTrue(titleRepository.existsById(titleResponse2.getId()));
        try {
            titleService.delete(titleResponse2.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    void shouldUpdateTitle(){
        //Given
        TitleSaveRequest titleSaveRequest = new TitleSaveRequest("String", "String", 0);
        TitleResponse titleSaveResponse = titleService.save(titleSaveRequest);
        TitleUpdateRequest titleUpdateRequest = new TitleUpdateRequest(titleSaveResponse.getId(), "Not a String", "Still a String", 1);
        //When
        TitleResponse titleUpdateResponse = titleService.update(titleUpdateRequest);
        //Then
        assertEquals(titleSaveResponse.getId(), titleUpdateResponse.getId());
        assertNotEquals(titleSaveResponse, titleUpdateResponse);
        try {
            titleService.delete(titleUpdateResponse.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    void shouldNotUpdateDeletedTitle(){
        //Given
        TitleSaveRequest titleSaveRequest = new TitleSaveRequest("String", "String", 0);
        TitleResponse titleSaveResponse = titleService.save(titleSaveRequest);
        TitleUpdateRequest titleUpdateRequest = new TitleUpdateRequest(titleSaveResponse.getId(), "Not a String", "Still a String", 1);
        titleService.delete(titleSaveResponse.getId());
        //When & Then
        assertThrows(TitleNotFoundException.class, () -> titleService.update(titleUpdateRequest));
        try {
            titleService.delete(titleSaveResponse.getId());
        } catch (Exception e) {
            //do nothing
        }
    }
}
