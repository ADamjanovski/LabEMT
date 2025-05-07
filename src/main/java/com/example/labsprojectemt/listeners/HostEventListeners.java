package com.example.labsprojectemt.listeners;

import com.example.labsprojectemt.events.HostCreatedEvent;
import com.example.labsprojectemt.repository.HostsPerCountryViewRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostEventListeners {


    private final HostsPerCountryViewRepository hostsPerCountryViewRepository;

    public HostEventListeners(HostsPerCountryViewRepository hostsPerCountryViewRepository) {
        this.hostsPerCountryViewRepository = hostsPerCountryViewRepository;
    }

    @EventListener
    public void onHostCreated(HostCreatedEvent event){
        hostsPerCountryViewRepository.refreshMaterializedView();
    }
}
