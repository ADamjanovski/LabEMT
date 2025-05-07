package com.example.labsprojectemt.service.domain;

import com.example.labsprojectemt.domain.Accommodation;
import com.example.labsprojectemt.domain.dto.CreateAccommodationDto;
import com.example.labsprojectemt.domain.views.AccommodationsPerHostView;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AccommodationService {

    Optional<Accommodation> save(Accommodation accommodation);

    List<Accommodation> availableAccommodation(@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate startDate,@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate);

    Optional<Accommodation> edit(Long id, CreateAccommodationDto createAccommodationDto);

    void deleteById(Long id);

    Optional<Accommodation> findById(Long id);

    List<Accommodation> findAll();
    List<AccommodationsPerHostView> findAllAccommodationsByHost();
}
