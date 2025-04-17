package com.example.labsprojectemt.service.application.impl;

import com.example.labsprojectemt.domain.dto.CreateCountryDto;
import com.example.labsprojectemt.domain.dto.DisplayCountryDto;
import com.example.labsprojectemt.service.application.CountryApplicationService;
import com.example.labsprojectemt.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryAppServiceImpl implements CountryApplicationService {

    private final CountryService countryService;

    public CountryAppServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public Optional<DisplayCountryDto> save(CreateCountryDto country) {
        return countryService.save(country.toCountry()).map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> delete(Long id) {
        return countryService.delete(id).map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> findById(Long id) {
        return countryService.findById(id).map(DisplayCountryDto::from);
    }
    @Override
    public List<DisplayCountryDto> findAll() {
        return DisplayCountryDto.from(countryService.findAll());
    }

    @Override
    public DisplayCountryDto update(Long id, String name, String continent) {
        return DisplayCountryDto.from(countryService.update(id,name,continent));
    }

    @Override
    public void deleteById(Long id) {
        countryService.deleteById(id);
    }
}
