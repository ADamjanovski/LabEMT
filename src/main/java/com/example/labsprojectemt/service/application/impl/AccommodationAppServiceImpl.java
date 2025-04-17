package com.example.labsprojectemt.service.application.impl;

import com.example.labsprojectemt.domain.dto.CreateAccommodationDto;
import com.example.labsprojectemt.domain.dto.DisplayAccommodationDto;
import com.example.labsprojectemt.service.application.AccommodationApplicationService;
import com.example.labsprojectemt.service.application.HostApplicationService;
import com.example.labsprojectemt.service.domain.AccommodationService;
import com.example.labsprojectemt.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AccommodationAppServiceImpl implements AccommodationApplicationService {

    private  final  AccommodationService accommodationService;
    private final HostService hostService;

    public AccommodationAppServiceImpl(AccommodationService accommodationService, HostService hostService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
    }

    @Override
    public Optional<DisplayAccommodationDto> save(CreateAccommodationDto accommodation) {
        return accommodationService.save(accommodation.toAccommodation(hostService.findById(accommodation.host()).get())).map(DisplayAccommodationDto::from);
    }

    @Override
    public List<DisplayAccommodationDto> availableAccommodation(LocalDate startDate, LocalDate endDate) {
        return DisplayAccommodationDto.from(accommodationService.availableAccommodation(startDate,endDate));
    }

    @Override
    public Optional<DisplayAccommodationDto> edit(Long id, CreateAccommodationDto createAccommodationDto) {
        return accommodationService.edit(id,createAccommodationDto).map(DisplayAccommodationDto::from);
    }

    @Override
    public void deleteById(Long id) {
        accommodationService.deleteById(id);
    }

    @Override
    public Optional<DisplayAccommodationDto> findById(Long id) {
        return accommodationService.findById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public List<DisplayAccommodationDto> findAll() {
        return DisplayAccommodationDto.from(accommodationService.findAll());
    }
}
