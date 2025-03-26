package com.example.labsprojectemt.service;

import com.example.labsprojectemt.model.Reservation;
import com.example.labsprojectemt.model.dto.ReservationDto;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationService {

    Optional<Reservation> save(Reservation reservation);

    List<Reservation> findAllBetweenDates(@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate startDate, @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate endDate);

    List<Reservation> findAll();

    Optional<Reservation> findById(Long id);

    void deleteById(Long id);
}
