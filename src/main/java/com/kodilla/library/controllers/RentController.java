package com.kodilla.library.controllers;

import com.kodilla.library.controllers.exceptions.BookNotFoundException;
import com.kodilla.library.controllers.exceptions.InvalidStatusException;
import com.kodilla.library.controllers.exceptions.ReaderNotFoundException;
import com.kodilla.library.controllers.exceptions.RentNotFoundException;
import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.Rent;
import com.kodilla.library.domain.dto.RentRequest;
import com.kodilla.library.domain.dto.RentResponse;
import com.kodilla.library.mapper.RentMapper;
import com.kodilla.library.service.RentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library/rent")
public class RentController {

    private RentMapper rentMapper;
    private RentService rentService;

    public RentController(RentMapper rentMapper, RentService rentService) {
        this.rentMapper = rentMapper;
        this.rentService = rentService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public RentResponse rentBook(@RequestBody RentRequest rentRequest)
            throws BookNotFoundException, ReaderNotFoundException, InvalidStatusException {
        Rent rent = rentMapper.mapToRent(rentRequest);
        rent = rentService.rentBook(rent);
        return rentMapper.mapToRentResponse(rent);
    }

    @GetMapping("/{id}")
    public RentResponse getRent(@PathVariable("id") Long id)
            throws RentNotFoundException {
        Rent rent = rentService.get(id);
        return rentMapper.mapToRentResponse(rent);
    }

    @GetMapping()
    public List<RentResponse> getRents() {
        List<Rent> rents = rentService.get();
        return rentMapper.mapToRentResponseList(rents);
    }

    @PutMapping("/{id}")
    public RentResponse returnBook(@PathVariable("id") Long id) throws RentNotFoundException {
        Rent rent = rentService.get(id);
        rent.getBook().setStatus(Book.Status.AVAILABLE);
        return rentMapper.mapToRentResponse(rent);
    }
}
