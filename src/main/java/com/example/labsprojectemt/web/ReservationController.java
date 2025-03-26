package com.example.labsprojectemt.web;

import com.example.labsprojectemt.model.dto.AccommodationDto;
import com.example.labsprojectemt.model.dto.ReservationDto;
import com.example.labsprojectemt.service.AccommodationService;
import com.example.labsprojectemt.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservations")
@Tag(name = "reservations API", description = "Endpoints for managing reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final AccommodationService accommodationService;

    public ReservationController(ReservationService reservationService, AccommodationService accommodationService) {
        this.reservationService = reservationService;
        this.accommodationService = accommodationService;
    }
    @Operation(summary = "Get all reservations", description = "Retrieves a list of all available reservations.")
    @GetMapping
    public List<ReservationDto> findAll() {
        return reservationService.findAll().stream().map(ReservationDto::from).collect(Collectors.toList());
    }
    @Operation(summary = "Get reservation by ID", description = "Finds a reservation by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> findById(@PathVariable Long id) {
        return reservationService.findById(id)
                .map(ReservationDto::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(
            summary = "Add a new reservation",
            description = "Creates a new reservation based on the given reservationdto."
    )
    @PostMapping("/add")
    public ResponseEntity<ReservationDto> save(@RequestBody ReservationDto createProductDto) {
        if(accommodationService.findById(createProductDto.accommodation()).isPresent()){
            return reservationService.save(createProductDto.toReservation(accommodationService.findById(createProductDto.accommodation()).get()))
                    .map(ReservationDto::from)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }

        return  ResponseEntity.notFound().build();
    }
    @Operation(summary = "Delete a product", description = "Deletes a product by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (reservationService.findById(id).isPresent()) {
            reservationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
