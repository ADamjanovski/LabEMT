package com.example.labsprojectemt.config;

import com.example.labsprojectemt.domain.Country;
import com.example.labsprojectemt.domain.User;
import com.example.labsprojectemt.domain.enumerations.Role;
import com.example.labsprojectemt.repository.CountryRepository;
import com.example.labsprojectemt.repository.HostRepository;
import com.example.labsprojectemt.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;


    public DataInitializer(

            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            CountryRepository countryRepository,
            HostRepository hostRepository
    ) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.countryRepository=countryRepository;
        this.hostRepository=hostRepository;
    }


    @PostConstruct
    public void init() {
        countryRepository.save(new Country("Macedonia","Europe"));
        countryRepository.save(new Country("Japan","Asia"));
        countryRepository.save(new Country("Canada","America"));


        //        categoryRepository.save(new Category("Food", "Food category"));
//        categoryRepository.save(new Category("Music", "Music category"));
//
//        manufacturerRepository.save(new Manufacturer("Nike", "USA"));
//        manufacturerRepository.save(new Manufacturer("KFC", "USA"));
//        manufacturerRepository.save(new Manufacturer("A Records", "UK"));

        userRepository.save(new User(
                "ad",
                passwordEncoder.encode("ad"),
                "Andrej Damjanovski",
                Role.ROLE_USER
        ));
        userRepository.save(new User(
                "admin",
                passwordEncoder.encode("admin"),
                "host",
                Role.ROLE_HOST
        ));
    }
}
