package com.example.labsprojectemt.model.dto;

import com.example.labsprojectemt.model.Accommodation;
import com.example.labsprojectemt.model.Reservation;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public record ReservationDto(Long accommodation,     @JsonFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                             @JsonFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

    public static ReservationDto from(Reservation reservation){
        return new ReservationDto(
                reservation.getAccommodation().getId(),
                reservation.getStartDate(),
                reservation.getEndDate()
        );
    }

    public Reservation toReservation(Accommodation accommodation){
        return new Reservation(accommodation,startDate,endDate);
    }
    public static List<ReservationDto> from(List<Reservation> reservations){
        return reservations.stream().map(ReservationDto::from).collect(Collectors.toList());
    }
}
