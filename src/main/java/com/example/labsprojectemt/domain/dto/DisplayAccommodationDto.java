package com.example.labsprojectemt.domain.dto;

import com.example.labsprojectemt.domain.Accommodation;
import com.example.labsprojectemt.domain.Host;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAccommodationDto(String name, String category, Long host, int numRooms) {

    public static DisplayAccommodationDto from(Accommodation accommodation){
        return new DisplayAccommodationDto(
                accommodation.getName(),
                accommodation.getCategory().name(),
                accommodation.getHost().getId(),
                accommodation.getNumRooms()
        );
    }
    public static List<DisplayAccommodationDto> from(List<Accommodation> accommodations) {
        return accommodations.stream().map(DisplayAccommodationDto::from).collect(Collectors.toList());
    }
    public Accommodation toAccommodation(Host host){
        return new Accommodation(name,category,host,numRooms);
    }
}