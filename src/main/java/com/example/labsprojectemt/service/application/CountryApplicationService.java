package com.example.labsprojectemt.service.application;

import com.example.labsprojectemt.domain.Country;
import com.example.labsprojectemt.domain.dto.CreateCountryDto;
import com.example.labsprojectemt.domain.dto.DisplayCountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    Optional<DisplayCountryDto> save(CreateCountryDto country);

    Optional<DisplayCountryDto> delete(Long id);
    Optional<DisplayCountryDto> findById(Long id);

    List<DisplayCountryDto> findAll();
    DisplayCountryDto update(Long id, String name, String continent);

    void deleteById(Long id);
}
