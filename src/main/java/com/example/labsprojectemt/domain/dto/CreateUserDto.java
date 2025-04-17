package com.example.labsprojectemt.domain.dto;

import com.example.labsprojectemt.domain.enumerations.Role;
import com.example.labsprojectemt.domain.User;

import java.util.List;
import java.util.stream.Collectors;

public record CreateUserDto(String username, String password,String repeatPassword, String name, String role) {


    public  User toUser(){
        return new User(this.username,this.name,this.password, Role.valueOf(this.role));
    }


    public static List<DisplayUserDto> from(List<User> users){
        return users.stream().map(DisplayUserDto::from).collect(Collectors.toList());
    }
}
