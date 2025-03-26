package com.example.labsprojectemt.service.impl;

import com.example.labsprojectemt.model.Country;
import com.example.labsprojectemt.repository.CountryRepository;
import com.example.labsprojectemt.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Country> save(Country country) {
        return Optional.of(countryRepository.save(country));
    }

    @Override
    public Optional<Country> delete(Long id) {
        Optional<Country> country=countryRepository.findById(id);
        countryRepository.deleteById(id);
        return country;
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country update(Long id, String name, String continent) {
        Country country=countryRepository.findById(id).orElseThrow();
        country.setContinent(continent);
        country.setName(name);
        return countryRepository.save(country);
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}
