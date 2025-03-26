package com.example.labsprojectemt.service;

import com.example.labsprojectemt.model.Country;
import com.example.labsprojectemt.model.Host;
import com.example.labsprojectemt.model.dto.HostDto;

import java.util.List;
import java.util.Optional;

public interface HostService {


    Optional<Host> save(Host host);
    Optional<Host> findById(Long id);

    List<Host> findAll();

    void deleteById(Long id);
}
