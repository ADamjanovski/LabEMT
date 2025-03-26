package com.example.labsprojectemt.model.dto;

import com.example.labsprojectemt.model.Country;
import com.example.labsprojectemt.model.Host;
import jakarta.persistence.ManyToOne;

import java.util.List;
import java.util.stream.Collectors;

public record HostDto( String name, String surname,Long country) {

    public static HostDto from(Host host) {
        return new HostDto(
                host.getName(),
                host.getSurname(),
                host.getCountry().getId()
        );
    }

    public Host toHost(Country country) {
        return new Host(name, surname, country);
    }
    public static List<HostDto> from(List<Host> hosts) {
        return hosts.stream().map(HostDto::from).collect(Collectors.toList());
    }
}
