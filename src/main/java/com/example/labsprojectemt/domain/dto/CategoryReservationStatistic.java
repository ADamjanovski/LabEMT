package com.example.labsprojectemt.domain.dto;

import com.example.labsprojectemt.domain.Country;

public record CategoryReservationStatistic (String category, Long reservations){


    public static CategoryReservationStatistic from(String category, Long reservations){
        return new CategoryReservationStatistic(
                category,
                reservations
        );
    }
}
