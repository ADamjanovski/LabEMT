package com.example.labsprojectemt.domain.dto;

import com.example.labsprojectemt.domain.Reservation;
import com.example.labsprojectemt.domain.enumerations.Role;
import com.example.labsprojectemt.domain.User;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayUserDto(String username, String name, Role role, List<Reservation> reservations) {

    public static DisplayUserDto from(User user){
        return new DisplayUserDto(
                user.getUsername(),
                user.getName(),
                user.getRole(),
                user.getReservations()
        );
    }

    public User toUser() {
        return new User(username, name, role.name(),reservations);
    }



    public static List<DisplayUserDto> from(List<User> users){
        return users.stream().map(DisplayUserDto::from).collect(Collectors.toList());
    }

}
