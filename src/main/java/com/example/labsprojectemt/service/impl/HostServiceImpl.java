package com.example.labsprojectemt.service.impl;

import com.example.labsprojectemt.model.Host;
import com.example.labsprojectemt.model.dto.HostDto;
import com.example.labsprojectemt.repository.HostRepository;
import com.example.labsprojectemt.service.CountryService;
import com.example.labsprojectemt.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;
    private final CountryService countryService;

    public HostServiceImpl(HostRepository hostRepository, CountryService countryService) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
    }

    @Override
    public Optional<Host> save(Host host) {
        return Optional.of(hostRepository.save(host));
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        hostRepository.deleteById(id);
    }
}
