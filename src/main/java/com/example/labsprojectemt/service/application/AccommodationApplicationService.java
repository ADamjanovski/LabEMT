package com.example.labsprojectemt.service.application;

import com.example.labsprojectemt.domain.Accommodation;
import com.example.labsprojectemt.domain.dto.CreateAccommodationDto;
import com.example.labsprojectemt.domain.dto.DisplayAccommodationDto;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {

    Optional<DisplayAccommodationDto> save(CreateAccommodationDto accommodation);

    List<DisplayAccommodationDto> availableAccommodation(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate);

    Optional<DisplayAccommodationDto> edit(Long id, CreateAccommodationDto createAccommodationDto);

    void deleteById(Long id);

    Optional<DisplayAccommodationDto> findById(Long id);

    List<DisplayAccommodationDto> findAll();
}
