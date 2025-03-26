package com.example.labsprojectemt.model.dto;

import com.example.labsprojectemt.model.Accommodation;
import com.example.labsprojectemt.model.Host;

import java.util.List;
import java.util.stream.Collectors;

public record AccommodationDto(String name, String category, Long host, int numRooms) {

    public static AccommodationDto from(Accommodation accommodation){
        return new AccommodationDto(
                accommodation.getName(),
                accommodation.getCategory().name(),
                accommodation.getHost().getId(),
                accommodation.getNumRooms()
        );
    }
    public static List<AccommodationDto> from(List<Accommodation> accommodations) {
        return accommodations.stream().map(AccommodationDto::from).collect(Collectors.toList());
    }
    public Accommodation toAccommodation(Host host){
        return new Accommodation(name,category,host,numRooms);
    }
}
