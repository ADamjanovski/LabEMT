package com.example.labsprojectemt.service.domain;

import com.example.labsprojectemt.domain.Reservation;
import com.example.labsprojectemt.domain.dto.CategoryReservationStatistic;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationService {

    Optional<Reservation> save(Reservation reservation);

    List<Reservation> findAllBetweenDates(@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate startDate, @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate endDate);

    List<Reservation> findAll();

    Optional<Reservation> findById(Long id);

    void deleteById(Long id);
    Optional<Reservation> confirmReservation(Long reservationId);
    Optional<Reservation> makeTemporaryReservation(Reservation reservation);
    boolean isReserved(Long id,LocalDate startDate,LocalDate endDate);

    List<CategoryReservationStatistic> categoryReservationStatistic();
}
