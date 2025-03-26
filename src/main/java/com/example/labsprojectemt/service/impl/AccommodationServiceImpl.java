package com.example.labsprojectemt.service.impl;

import com.example.labsprojectemt.model.Accommodation;
import com.example.labsprojectemt.model.Host;
import com.example.labsprojectemt.model.dto.AccommodationDto;
import com.example.labsprojectemt.repository.AccommodationRepository;
import com.example.labsprojectemt.service.AccommodationService;
import com.example.labsprojectemt.service.HostService;
import com.example.labsprojectemt.service.ReservationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final HostService hostService;
    private final ReservationService reservationService;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostService hostService, ReservationService reservationService) {
        this.accommodationRepository = accommodationRepository;
        this.hostService = hostService;
        this.reservationService = reservationService;
    }

    @Override
    public Optional<Accommodation> save(Accommodation accommodation) {
        return Optional.of(accommodationRepository.save(accommodation));

    }

    @Override
    public List<Accommodation> availableAccommodation( @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate startDate, @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate endDate) {
        List<Long> accommodationsIds=reservationService.findAllBetweenDates(startDate,endDate)
                .stream().map(reservation -> reservation.getAccommodation().getId()).collect(Collectors.toList());

        if(accommodationsIds.isEmpty())
            return accommodationRepository.findAll();
        return accommodationRepository.findAllByIdNotIn(accommodationsIds);
    }

    @Override
    public Optional<Accommodation> edit(Long id, AccommodationDto accommodationDto) {
        Accommodation accommodation=accommodationRepository.findById(id).orElseThrow();
        accommodation.setName(accommodationDto.name());
        accommodation.setCategoryByName(accommodationDto.category());
        accommodation.setNumRooms(accommodation.getNumRooms());
        Host host=hostService.findById(accommodationDto.host()).orElseThrow();
        accommodation.setHost(host);
        return    Optional.of(accommodationRepository.save(accommodation));
    }

    @Override
    public void deleteById(Long id) {
        accommodationRepository.deleteById(id);
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }


}
