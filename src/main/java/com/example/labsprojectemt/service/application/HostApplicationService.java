package com.example.labsprojectemt.service.application;

import com.example.labsprojectemt.domain.Host;
import com.example.labsprojectemt.domain.dto.CreateHostDto;
import com.example.labsprojectemt.domain.dto.DisplayHostDto;
import com.example.labsprojectemt.domain.projections.HostProjection;
import com.example.labsprojectemt.domain.views.HostsPerCountryView;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    Optional<DisplayHostDto> save(CreateHostDto host);
    Optional<DisplayHostDto> findById(Long id);

    List<DisplayHostDto> findAll();

    void deleteById(Long id);

    List<HostsPerCountryView> findNumHostsPerCountry();

    List<HostProjection> findAllProjections();
}
