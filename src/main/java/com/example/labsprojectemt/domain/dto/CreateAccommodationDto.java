package com.example.labsprojectemt.domain.dto;

import com.example.labsprojectemt.domain.Accommodation;
import com.example.labsprojectemt.domain.Host;

import java.util.List;
import java.util.stream.Collectors;

public record CreateAccommodationDto(String name, String category, Long host, int numRooms) {

    public static CreateAccommodationDto from(Accommodation accommodation){
        return new CreateAccommodationDto(
                accommodation.getName(),
                accommodation.getCategory().name(),
                accommodation.getHost().getId(),
                accommodation.getNumRooms()
        );
    }
    public static List<CreateAccommodationDto> from(List<Accommodation> accommodations) {
        return accommodations.stream().map(CreateAccommodationDto::from).collect(Collectors.toList());
    }
    public Accommodation toAccommodation(Host host){
        return new Accommodation(name,category,host,numRooms);
    }
}
