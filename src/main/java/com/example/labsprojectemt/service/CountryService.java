package com.example.labsprojectemt.service;

import com.example.labsprojectemt.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {


    Optional<Country> save(Country country);

    Optional<Country> delete(Long id);
    Optional<Country> findById(Long id);

    List<Country> findAll();
    Country update(Long id, String name, String continent);

    void deleteById(Long id);
}
