package com.example.labsprojectemt.web;

import com.example.labsprojectemt.domain.dto.CreateUserDto;
import com.example.labsprojectemt.domain.dto.DisplayUserDto;
import com.example.labsprojectemt.domain.dto.LoginResponseDto;
import com.example.labsprojectemt.domain.dto.LoginUserDto;
import com.example.labsprojectemt.service.application.UserApplicationService;
import com.example.labsprojectemt.service.exceptions.InvalidUserCredentialsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User API", description = "Endpoints for user authentication and registration") // Swagger tag
public class UserController {


        private final UserApplicationService userApplicationService;

        public UserController(UserApplicationService userApplicationService) {
            this.userApplicationService = userApplicationService;
        }

        @Operation(summary = "Register a new user", description = "Creates a new user account")
        @ApiResponses(
                value = {@ApiResponse(
                        responseCode = "200",
                        description = "User registered successfully"
                ), @ApiResponse(
                        responseCode = "400", description = "Invalid input or passwords do not match"
                )}
        )
        @PostMapping("/register")
        public ResponseEntity<DisplayUserDto> register(@RequestBody CreateUserDto createUserDto) {
                return userApplicationService.save(createUserDto)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());

        }

        @Operation(summary = "User login", description = "Authenticates a user and starts a session")
        @ApiResponses(
                value = {@ApiResponse(
                        responseCode = "200",
                        description = "User authenticated successfully"
                ), @ApiResponse(responseCode = "404", description = "Invalid username or password")}
        )
        @PostMapping("/login")
        public ResponseEntity<LoginResponseDto> login(@RequestBody LoginUserDto loginUserDto) {
            try {
                return userApplicationService.login(loginUserDto)
                        .map(ResponseEntity::ok)
                        .orElseThrow(InvalidUserCredentialsException::new);
            } catch (InvalidUserCredentialsException e) {
                return ResponseEntity.notFound().build();
            }
        }

        @Operation(summary = "User logout", description = "Ends the user's session")
        @ApiResponse(responseCode = "200", description = "User logged out successfully")
        @GetMapping("/logout")
        public void logout(HttpServletRequest request) {
            request.getSession().invalidate();
        }

    }
