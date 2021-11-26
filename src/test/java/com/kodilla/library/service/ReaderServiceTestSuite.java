package com.kodilla.library.service;

import com.kodilla.library.controllers.exceptions.ReaderNotFoundException;
import com.kodilla.library.domain.Reader;
import com.kodilla.library.domain.dto.ReaderResponse;
import com.kodilla.library.domain.dto.ReaderSaveRequest;
import com.kodilla.library.domain.dto.ReaderUpdateRequest;
import com.kodilla.library.repository.ReaderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReaderServiceTestSuite {

    @Autowired
    private ReaderService readerService;

    @Autowired
    private ReaderRepository readerRepository;

    @Test
    void shouldSaveReader(){
        //Given
        ReaderSaveRequest readerSaveRequest = new ReaderSaveRequest("String", "String");
        //When
        ReaderResponse readerResponse = readerService.save(readerSaveRequest);
        //Then
        assertEquals(readerSaveRequest.getFirstname(), readerResponse.getFirstname());
        assertEquals(readerSaveRequest.getLastname(), readerResponse.getLastname());
        //CleanUp
        try {
            readerService.delete(readerResponse.getId());
        }
        catch (Exception e){
            //do nothing
        }
    }

    @Test
    void shouldGetAllReaders(){
        //Given
        ReaderSaveRequest readerSaveRequest = new ReaderSaveRequest("String", "String");
        ReaderResponse readerResponse1 = readerService.save(readerSaveRequest);
        ReaderResponse readerResponse2 = readerService.save(readerSaveRequest);
        ReaderResponse readerResponse3 = readerService.save(readerSaveRequest);
        //When
        List<ReaderResponse> readerResponseList = readerService.get();
        //Then
        assertEquals(3, readerResponseList.size());
        assertTrue(readerResponseList.contains(readerResponse1));
        assertTrue(readerResponseList.contains(readerResponse2));
        assertTrue(readerResponseList.contains(readerResponse3));
        //CleanUp
        try {
            readerService.delete(readerResponse1.getId());
            readerService.delete(readerResponse2.getId());
            readerService.delete(readerResponse3.getId());
        }
        catch (Exception e){
            //do nothing
        }
    }

    @Test
    void shouldGetOneReader(){
        //Given
        ReaderSaveRequest readerSaveRequest = new ReaderSaveRequest("String", "String");
        ReaderResponse readerResponse1 = readerService.save(readerSaveRequest);
        ReaderResponse readerResponse2 = readerService.save(readerSaveRequest);
        //When
        ReaderResponse readerResponse = readerService.get(readerResponse1.getId());
        //Then
        assertEquals(readerResponse1.getFirstname(), readerResponse.getFirstname());
        assertEquals(readerResponse1.getLastname(), readerResponse.getLastname());
        assertNotEquals(readerResponse2, readerResponse);
        //CleanUp
        try {
            readerService.delete(readerResponse1.getId());
            readerService.delete(readerResponse2.getId());
        }
        catch (Exception e){
            //do nothing
        }
    }

    @Test
    void shouldNotFetchDeletedReader(){
        //Given
        ReaderSaveRequest readerSaveRequest = new ReaderSaveRequest("String", "String");
        ReaderResponse readerResponse1 = readerService.save(readerSaveRequest);
        readerService.delete(readerResponse1.getId());
        //When & Then
        assertThrows(ReaderNotFoundException.class, () -> readerService.get(readerResponse1.getId()));
        //CleanUp
        try {
            readerService.delete(readerResponse1.getId());
        }
        catch (Exception e){
            //do nothing
        }
    }

    @Test
    void shouldDeleteReader(){
        //Given
        ReaderSaveRequest readerSaveRequest = new ReaderSaveRequest("String", "String");
        ReaderResponse readerResponse = readerService.save(readerSaveRequest);
        //When
        readerService.delete(readerResponse.getId());
        //Then
        assertFalse(readerRepository.existsById(readerResponse.getId()));
        //CleanUp
        try {
            readerService.delete(readerResponse.getId());
        }
        catch (Exception e){
            //do nothing
        }
    }

    @Test
    void shouldUpdateReader(){
        //Given
        ReaderSaveRequest readerSaveRequest = new ReaderSaveRequest("String", "String");
        ReaderResponse savedReaderResponse = readerService.save(readerSaveRequest);
        ReaderUpdateRequest readerUpdateRequest = new ReaderUpdateRequest(savedReaderResponse.getId(), "Not a String", "Still a String");
        //When
        ReaderResponse updatedReaderResponse = readerService.update(readerUpdateRequest);
        //Then
        assertEquals(savedReaderResponse.getId(), updatedReaderResponse.getId());
        assertNotEquals(savedReaderResponse, updatedReaderResponse);
        //CleanUp
        try {
            readerService.delete(updatedReaderResponse.getId());
        }
        catch (Exception e){
            //do nothing
        }
    }

    @Test
    void shouldNotUpdateDeletedReader(){
        //Given
        ReaderSaveRequest readerSaveRequest = new ReaderSaveRequest("String", "String");
        ReaderResponse savedReaderResponse = readerService.save(readerSaveRequest);
        readerService.delete(savedReaderResponse.getId());
        ReaderUpdateRequest readerUpdateRequest = new ReaderUpdateRequest(savedReaderResponse.getId(), "Not a String", "Still a String");
        //When & Then
        assertThrows(ReaderNotFoundException.class, () -> readerService.update(readerUpdateRequest));
        //CleanUp
        try {
            readerService.delete(savedReaderResponse.getId());
        }
        catch (Exception e){
            //do nothing
        }
    }

}
