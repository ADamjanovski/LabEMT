package com.example.labsprojectemt.service.application.impl;

import com.example.labsprojectemt.domain.dto.CreateHostDto;
import com.example.labsprojectemt.domain.dto.DisplayHostDto;
import com.example.labsprojectemt.service.application.HostApplicationService;
import com.example.labsprojectemt.service.domain.CountryService;
import com.example.labsprojectemt.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HostAppImplService implements HostApplicationService {

    private final HostService hostService;
    private final CountryService countryService;

    public HostAppImplService(HostService hostService, CountryService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }

    @Override
    public Optional<DisplayHostDto> save(CreateHostDto host) {
        return Optional.of(DisplayHostDto.from(hostService.save(host.toHost(countryService.findById(host.country()).get())).get()));
    }

    @Override
    public Optional<DisplayHostDto> findById(Long id) {
        return Optional.of(DisplayHostDto.from(hostService.findById(id).get()));
    }

    @Override
    public List<DisplayHostDto> findAll() {
        return hostService.findAll().stream().map(DisplayHostDto::from).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        hostService.deleteById(id);
    }
}
