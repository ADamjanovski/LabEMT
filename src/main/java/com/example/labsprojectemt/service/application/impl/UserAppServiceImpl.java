package com.example.labsprojectemt.service.application.impl;

import com.example.labsprojectemt.domain.User;
import com.example.labsprojectemt.domain.dto.CreateUserDto;
import com.example.labsprojectemt.domain.dto.DisplayUserDto;
import com.example.labsprojectemt.domain.dto.LoginResponseDto;
import com.example.labsprojectemt.domain.dto.LoginUserDto;
import com.example.labsprojectemt.security.JwtHelper;
import com.example.labsprojectemt.service.application.UserApplicationService;
import com.example.labsprojectemt.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserAppServiceImpl implements UserApplicationService {

    private final UserService userService;
    private final JwtHelper jwtHelper;

    public UserAppServiceImpl(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public Optional<DisplayUserDto> save(CreateUserDto user) {
        return userService.save(user.toUser()).map(DisplayUserDto::from);
    }

    @Override
    public List<DisplayUserDto> findAll() {
        return userService.findAll().stream().map(DisplayUserDto::from).collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayUserDto> findById(Long id) {
        return userService.findById(id).map(DisplayUserDto::from);
    }

    @Override
    public void deleteById(Long id) {
        userService.deleteById(id);
    }

    @Override
    public Optional<LoginResponseDto> login(LoginUserDto loginUserDto) {
        User user = userService.login(
                loginUserDto.username(),
                loginUserDto.password()
        );

        String token = jwtHelper.generateToken(user);

        return Optional.of(new LoginResponseDto(token));

    }
}
