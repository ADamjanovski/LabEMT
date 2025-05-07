package com.example.labsprojectemt.web;

import com.example.labsprojectemt.domain.User;
import com.example.labsprojectemt.domain.dto.CategoryReservationStatistic;
import com.example.labsprojectemt.domain.dto.CreateReservationDto;
import com.example.labsprojectemt.domain.dto.DisplayReservationDto;
import com.example.labsprojectemt.service.application.AccommodationApplicationService;
import com.example.labsprojectemt.service.application.ReservationApplicationService;
import com.example.labsprojectemt.service.domain.ReservationService;
import com.example.labsprojectemt.service.exceptions.AccommodationAlreadyReserved;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservations")
@Tag(name = "reservations API", description = "Endpoints for managing reservations")
public class ReservationController {

    private final ReservationApplicationService reservationService;
    private final AccommodationApplicationService accommodationApplicationService;

    public ReservationController(ReservationApplicationService reservationService,  AccommodationApplicationService accommodationApplicationService) {
        this.reservationService = reservationService;
        this.accommodationApplicationService = accommodationApplicationService;

    }
    @Operation(summary = "Get all reservations", description = "Retrieves a list of all available reservations.")
    @GetMapping
    public List<DisplayReservationDto> findAll() {
        return reservationService.findAll();
    }
    @Operation(summary = "Get reservation by ID", description = "Finds a reservation by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayReservationDto> findById(@PathVariable Long id) {
        return reservationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(
            summary = "Add a new reservation",
            description = "Creates a new reservation based on the given reservationdto."
    )
    @PostMapping("/add")
    @ApiResponse(responseCode = "500", description = "Accommodation already reserved.")
    public ResponseEntity<DisplayReservationDto> save(@RequestBody CreateReservationDto createProductDto, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        try{
            if(accommodationApplicationService.findById(createProductDto.accommodation()).isPresent()){
                return reservationService.save(createProductDto,user)
                        .map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
            }
        }catch (AccommodationAlreadyReserved e){
            return  ResponseEntity.internalServerError().build();
        }


        return  ResponseEntity.notFound().build();
    }
    @PostMapping("/add/temporaryReservation")
    public ResponseEntity<DisplayReservationDto> temporaryReservation(@RequestBody CreateReservationDto createProductDto, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        if(accommodationApplicationService.findById(createProductDto.accommodation()).isPresent()){
            return reservationService.makeTemporaryReservation(createProductDto,user)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }

        return  ResponseEntity.notFound().build();
    }
    @PostMapping("/confirmReservation/{id}")
    @ApiResponse(responseCode = "500", description = "Accommodation already reserved.")

    public ResponseEntity<DisplayReservationDto> temporaryReservation(@PathVariable Long id) {
        try {
            return reservationService.confirmReservation(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }catch (AccommodationAlreadyReserved ex){
        return  ResponseEntity.internalServerError().build();
        }
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


    @GetMapping("/categoryStatistic")
    @Operation(summary = "Get statistics per category", description = "Get number of reservations per category.")
    public List<CategoryReservationStatistic>getCategoryStatistic(){
        return reservationService.categoryReservationStatistic();
    }

}
