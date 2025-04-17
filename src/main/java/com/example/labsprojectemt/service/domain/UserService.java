package com.example.labsprojectemt.service.domain;

import com.example.labsprojectemt.domain.Reservation;
import com.example.labsprojectemt.domain.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> save(User user);


    List<User> findAll();

    Optional<User> findById(Long id);

    void deleteById(Long id);

    User login(String username, String password);

    Optional<User> loadUserByUsername(String username);
}
