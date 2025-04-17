package com.example.labsprojectemt.service.domain.impl;

import com.example.labsprojectemt.domain.Reservation;
import com.example.labsprojectemt.domain.enumerations.ReservationStatus;
import com.example.labsprojectemt.repository.ReservationRepository;
import com.example.labsprojectemt.service.domain.ReservationService;
import com.example.labsprojectemt.service.exceptions.AccommodationAlreadyReserved;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        reservation.setStatus(ReservationStatus.CONFIRMED);

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

    @Override
    public Optional<Reservation> confirmReservation(Long reservationId) {
        Reservation reservation=reservationRepository.findById(reservationId).get();
        if(reservationRepository.findIfReserved(reservation.getAccommodation().getId(),reservation.getStartDate(),reservation.getEndDate()).isPresent()){
            throw new AccommodationAlreadyReserved();
        }
        reservation.setStatus(ReservationStatus.CONFIRMED);

        reservationRepository.save(reservation);
        return Optional.of(reservation);
    }

    @Override
    public Optional<Reservation> makeTemporaryReservation(Reservation reservation) {
        reservation.setStatus(ReservationStatus.PENDING);
        reservationRepository.save(reservation);
        return Optional.of(reservation);
    }

    @Override
    public boolean isReserved(Long id,LocalDate startDate, LocalDate endDate) {
        return false;
    }
}
