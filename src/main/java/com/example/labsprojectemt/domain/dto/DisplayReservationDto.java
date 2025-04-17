package com.example.labsprojectemt.domain.dto;

import com.example.labsprojectemt.domain.Accommodation;
import com.example.labsprojectemt.domain.Reservation;
import com.example.labsprojectemt.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record DisplayReservationDto(Long accommodation, @JsonFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                    @JsonFormat(pattern = "yyyy-MM-dd") LocalDate endDate,String status) {

    public static DisplayReservationDto from(Reservation reservation){
        return new DisplayReservationDto(
                reservation.getAccommodation().getId(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getStatus().name()
        );
    }

    public Reservation toReservation(Accommodation accommodation, User user){
        return new Reservation(accommodation,startDate,endDate,user);
    }
    public static List<DisplayReservationDto> from(List<Reservation> reservations){
        return reservations.stream().map(DisplayReservationDto::from).collect(Collectors.toList());
    }
}