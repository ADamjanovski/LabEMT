package com.example.labsprojectemt.domain.dto;

import com.example.labsprojectemt.domain.Country;
import com.example.labsprojectemt.domain.Host;

import java.util.List;
import java.util.stream.Collectors;

public record CreateHostDto(String name, String surname, Long country) {

    public static CreateHostDto from(Host host) {
        return new CreateHostDto(
                host.getName(),
                host.getSurname(),
                host.getCountry().getId()
        );
    }

    public Host toHost(Country country) {
        return new Host(name, surname, country);
    }
    public static List<CreateHostDto> from(List<Host> hosts) {
        return hosts.stream().map(CreateHostDto::from).collect(Collectors.toList());
    }
}
