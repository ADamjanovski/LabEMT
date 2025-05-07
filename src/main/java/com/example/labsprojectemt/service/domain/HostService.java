package com.example.labsprojectemt.service.domain;

import com.example.labsprojectemt.domain.Host;
import com.example.labsprojectemt.domain.projections.HostProjection;
import com.example.labsprojectemt.domain.views.HostsPerCountryView;

import java.util.List;
import java.util.Optional;

public interface HostService {


    Optional<Host> save(Host host);
    Optional<Host> findById(Long id);

    List<Host> findAll();

    void deleteById(Long id);
    List<HostsPerCountryView> findNumHostsPerCountry();

    List<HostProjection> findAllProjections();
}
