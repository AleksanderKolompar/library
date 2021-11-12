package com.kodilla.library.repository;

import com.kodilla.library.domain.Rent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RentRepository extends CrudRepository<Rent, Long> {

    @Override
    Rent save(Rent rent);

    @Override
    Optional<Rent> findById(Long id);

    @Override
    List<Rent> findAll();

    @Override
    void deleteById(Long id);
}
