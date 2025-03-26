package com.example.labsprojectemt.service;

import com.example.labsprojectemt.model.Accommodation;
import com.example.labsprojectemt.model.dto.AccommodationDto;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AccommodationService {

    Optional<Accommodation> save(Accommodation accommodation);

    List<Accommodation> availableAccommodation(@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate startDate,@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate);

    Optional<Accommodation> edit(Long id, AccommodationDto accommodationDto);

    void deleteById(Long id);

    Optional<Accommodation> findById(Long id);

    List<Accommodation> findAll();
}
