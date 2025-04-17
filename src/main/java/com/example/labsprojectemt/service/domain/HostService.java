package com.example.labsprojectemt.service.domain;

import com.example.labsprojectemt.domain.Host;

import java.util.List;
import java.util.Optional;

public interface HostService {


    Optional<Host> save(Host host);
    Optional<Host> findById(Long id);

    List<Host> findAll();

    void deleteById(Long id);
}
