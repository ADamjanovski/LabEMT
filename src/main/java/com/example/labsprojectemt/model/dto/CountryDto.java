package com.example.labsprojectemt.model.dto;

import com.example.labsprojectemt.model.Country;
import com.example.labsprojectemt.model.Host;

import java.util.List;
import java.util.stream.Collectors;

public record CountryDto(String name, String continent) {

    public static CountryDto from(Country country){
        return new CountryDto(
                country.getName(),
                country.getContinent()
        );
    }

    public Country toCountry() {
        return new Country(name, continent);
    }
    public static List<CountryDto> from(List<Country> countries) {
        return countries.stream().map(CountryDto::from).collect(Collectors.toList());
    }
}
