package com.kodilla.library.controllers;

import com.kodilla.library.controllers.exceptions.BookNotFoundException;
import com.kodilla.library.controllers.exceptions.InvalidStatusException;
import com.kodilla.library.controllers.exceptions.ReaderNotFoundException;
import com.kodilla.library.controllers.exceptions.RentNotFoundException;
import com.kodilla.library.domain.dto.RentRequest;
import com.kodilla.library.domain.dto.RentResponse;
import com.kodilla.library.service.RentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library/rent")
public class RentController {

    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public RentResponse rentBook(@RequestBody RentRequest rentRequest)
            throws BookNotFoundException, ReaderNotFoundException, InvalidStatusException {
        return rentService.rentBook(rentRequest);
    }

    @GetMapping("/{id}")
    public RentResponse getRent(@PathVariable("id") Long id)
            throws RentNotFoundException {
        return rentService.get(id);
    }

    @GetMapping()
    public List<RentResponse> getRents() {
        return rentService.get();
    }

    @PutMapping("/{id}")
    public RentResponse returnBook(@PathVariable("id") Long id)
            throws RentNotFoundException, InvalidStatusException {
        return rentService.returnBook(id);
    }
}
