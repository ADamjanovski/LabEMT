package com.example.labsprojectemt.domain.dto;

import com.example.labsprojectemt.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayCountryDto(String name, String continent) {

    public static DisplayCountryDto from(Country country){
        return new DisplayCountryDto(
                country.getName(),
                country.getContinent()
        );
    }

    public Country toCountry() {
        return new Country(name, continent);
    }
    public static List<DisplayCountryDto> from(List<Country> countries) {
        return countries.stream().map(DisplayCountryDto::from).collect(Collectors.toList());
    }
}
