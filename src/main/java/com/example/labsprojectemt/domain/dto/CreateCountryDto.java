package com.example.labsprojectemt.domain.dto;

import com.example.labsprojectemt.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record CreateCountryDto(String name, String continent) {

    public static CreateCountryDto from(Country country){
        return new CreateCountryDto(
                country.getName(),
                country.getContinent()
        );
    }

    public Country toCountry() {
        return new Country(name, continent);
    }
    public static List<CreateCountryDto> from(List<Country> countries) {
        return countries.stream().map(CreateCountryDto::from).collect(Collectors.toList());
    }
}
