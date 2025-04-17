package com.example.labsprojectemt.service.application.impl;

import com.example.labsprojectemt.domain.Reservation;
import com.example.labsprojectemt.domain.User;
import com.example.labsprojectemt.domain.dto.CreateReservationDto;
import com.example.labsprojectemt.domain.dto.DisplayReservationDto;
import com.example.labsprojectemt.service.application.ReservationApplicationService;
import com.example.labsprojectemt.service.domain.AccommodationService;
import com.example.labsprojectemt.service.domain.ReservationService;
import com.example.labsprojectemt.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationAppImplService implements ReservationApplicationService {

    private final ReservationService reservationService;
    private final AccommodationService accommodationService;
    private final UserService userService;

    public ReservationAppImplService(ReservationService reservationService, AccommodationService accommodationService, UserService userService) {
        this.reservationService = reservationService;
        this.accommodationService = accommodationService;
        this.userService = userService;
    }

    @Override
    public Optional<DisplayReservationDto> save(CreateReservationDto reservation, User user) {
        return reservationService.save(reservation.toReservation(accommodationService.findById(reservation.accommodation()).get(),
                        user))
                .map(DisplayReservationDto::from);
    }

    @Override
    public List<DisplayReservationDto> findAllBetweenDates(LocalDate startDate, LocalDate endDate) {
        return reservationService.findAllBetweenDates(startDate,endDate).stream().map(DisplayReservationDto::from).collect(Collectors.toList());
    }

    @Override
    public List<DisplayReservationDto> findAll() {
        return reservationService.findAll().stream().map(DisplayReservationDto::from).collect(Collectors.toList());

    }

    @Override
    public Optional<DisplayReservationDto> findById(Long id) {
        return reservationService.findById(id).map(DisplayReservationDto::from);
    }

    @Override
    public void deleteById(Long id) {
        reservationService.deleteById(id);
    }

    @Override
    public Optional<DisplayReservationDto> confirmReservation(Long reservationId) {
        return  reservationService.confirmReservation(reservationId).map(DisplayReservationDto::from);
    }

    @Override
    public Optional<DisplayReservationDto> makeTemporaryReservation(CreateReservationDto reservation,User user) {
        return reservationService
                .makeTemporaryReservation(reservation.
                        toReservation(accommodationService.findById(reservation.accommodation()).get(),
                user)).map(DisplayReservationDto::from);
    }
}
