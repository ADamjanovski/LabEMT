package com.example.labsprojectemt.service.domain.impl;

import com.example.labsprojectemt.domain.Host;
import com.example.labsprojectemt.domain.projections.HostProjection;
import com.example.labsprojectemt.domain.views.HostsPerCountryView;
import com.example.labsprojectemt.events.HostCreatedEvent;
import com.example.labsprojectemt.repository.HostRepository;
import com.example.labsprojectemt.repository.HostsPerCountryViewRepository;
import com.example.labsprojectemt.service.domain.CountryService;
import com.example.labsprojectemt.service.domain.HostService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;
    private final CountryService countryService;
    private final HostsPerCountryViewRepository hostsPerCountryViewRepository;
    private final ApplicationEventPublisher eventPublisher;


    public HostServiceImpl(HostRepository hostRepository, CountryService countryService, HostsPerCountryViewRepository hostsPerCountryViewRepository, ApplicationEventPublisher eventPublisher) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
        this.hostsPerCountryViewRepository = hostsPerCountryViewRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<Host> save(Host host) {

        Optional<Host> hostCreated=Optional.of(hostRepository.save(host));
        hostCreated.ifPresent(value -> eventPublisher.publishEvent(new HostCreatedEvent(value)));
        return hostCreated;
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

    @Override
    public List<HostsPerCountryView> findNumHostsPerCountry() {
        List<HostsPerCountryView> view= hostsPerCountryViewRepository.findAll();
        return view;
    }

    @Override
    public List<HostProjection> findAllProjections() {
        return hostRepository.takeNameAndSurnameByProjection();
    }
}
