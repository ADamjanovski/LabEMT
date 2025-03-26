package com.example.labsprojectemt.service.impl;

import com.example.labsprojectemt.model.Reservation;
import com.example.labsprojectemt.repository.ReservationRepository;
import com.example.labsprojectemt.service.AccommodationService;
import com.example.labsprojectemt.service.ReservationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl  implements ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Optional<Reservation> save(Reservation reservation) {
        return Optional.of(reservationRepository.save(reservation));
    }

    @Override
    public List<Reservation> findAllBetweenDates(@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate startDate, @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate endDate) {
        return reservationRepository.findAllBetweenDates(startDate,endDate);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }
}
