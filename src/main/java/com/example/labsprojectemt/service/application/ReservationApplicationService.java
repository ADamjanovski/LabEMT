package com.example.labsprojectemt.service.application;

import com.example.labsprojectemt.domain.Reservation;
import com.example.labsprojectemt.domain.User;
import com.example.labsprojectemt.domain.dto.CategoryReservationStatistic;
import com.example.labsprojectemt.domain.dto.CreateReservationDto;
import com.example.labsprojectemt.domain.dto.DisplayReservationDto;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationApplicationService {

    Optional<DisplayReservationDto> save(CreateReservationDto reservation, User user);

    List<DisplayReservationDto> findAllBetweenDates(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate endDate);

    List<DisplayReservationDto> findAll();

    Optional<DisplayReservationDto> findById(Long id);

    void deleteById(Long id);
    Optional<DisplayReservationDto> confirmReservation(Long reservationId);
    Optional<DisplayReservationDto> makeTemporaryReservation(CreateReservationDto reservation,User user);
    List<CategoryReservationStatistic> categoryReservationStatistic();

}
