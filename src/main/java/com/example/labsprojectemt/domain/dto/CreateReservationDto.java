package com.example.labsprojectemt.domain.dto;

import com.example.labsprojectemt.domain.Accommodation;
import com.example.labsprojectemt.domain.Reservation;
import com.example.labsprojectemt.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record CreateReservationDto(Long accommodation,@JsonFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                   @JsonFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

    public static CreateReservationDto from(Reservation reservation){
        return new CreateReservationDto(
                reservation.getAccommodation().getId(),
                reservation.getStartDate(),
                reservation.getEndDate()
        );
    }

    public Reservation toReservation(Accommodation accommodation, User user){
        return new Reservation(accommodation,startDate,endDate,user);
    }
    public static List<CreateReservationDto> from(List<Reservation> reservations){
        return reservations.stream().map(CreateReservationDto::from).collect(Collectors.toList());
    }
}
