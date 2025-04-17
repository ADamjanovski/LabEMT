package com.example.labsprojectemt.service.domain.impl;

import com.example.labsprojectemt.domain.User;
import com.example.labsprojectemt.repository.UserRepository;
import com.example.labsprojectemt.service.exceptions.InvalidArgumentsException;
import com.example.labsprojectemt.service.exceptions.InvalidUserCredentialsException;

import com.example.labsprojectemt.service.domain.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public Optional<User> save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return Optional.of(userRepository.save(user));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(
                InvalidUserCredentialsException::new);

    }

    @Override
    public Optional<User> loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
