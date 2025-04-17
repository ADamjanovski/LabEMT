package com.example.labsprojectemt.service.application;

import com.example.labsprojectemt.domain.User;
import com.example.labsprojectemt.domain.dto.CreateUserDto;
import com.example.labsprojectemt.domain.dto.DisplayUserDto;
import com.example.labsprojectemt.domain.dto.LoginUserDto;

import java.util.List;
import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDto> save(CreateUserDto user);


    List<DisplayUserDto> findAll();

    Optional<DisplayUserDto> findById(Long id);

    void deleteById(Long id);
    Optional<DisplayUserDto> login(LoginUserDto loginUserDto);


}
