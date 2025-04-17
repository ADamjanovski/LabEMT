package com.example.labsprojectemt.domain.dto;

import com.example.labsprojectemt.domain.Country;
import com.example.labsprojectemt.domain.Host;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayHostDto(String name, String surname, Long country) {

    public static DisplayHostDto from(Host host) {
        return new DisplayHostDto(
                host.getName(),
                host.getSurname(),
                host.getCountry().getId()
        );
    }

    public Host toHost(Country country) {
        return new Host(name, surname, country);
    }
    public static List<DisplayHostDto> from(List<Host> hosts) {
        return hosts.stream().map(DisplayHostDto::from).collect(Collectors.toList());
    }
}