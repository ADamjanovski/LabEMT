package com.example.labsprojectemt.web;

import com.example.labsprojectemt.model.dto.AccommodationDto;
import com.example.labsprojectemt.service.AccommodationService;
import com.example.labsprojectemt.service.HostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accommodation")
@Tag(name = "Accommodation API", description = "Endpoints for managing accommodations")
public class AccommodationController {
    private final AccommodationService accommodationService;
    private final HostService hostService;

    public AccommodationController(AccommodationService accommodationService, HostService hostService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
    }
    @Operation(summary = "Get all accommodation", description = "Retrieves a list of all available accommodation.")
    @GetMapping
    public List<AccommodationDto> findAll() {
        return accommodationService.findAll().stream().map(AccommodationDto::from).collect(Collectors.toList());
    }

    @Operation(summary = "Get all available accommodation", description = "Retrieves a list of all available accommodation.")
    @GetMapping("/availableAccommodation")
    public List<AccommodationDto> findAvailable(    @RequestParam
                                                        @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                        LocalDate startDate,
                                                    @RequestParam
                                                        @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                    LocalDate endDate
    ) {
        return accommodationService.availableAccommodation(startDate,endDate).stream().map(AccommodationDto::from).collect(Collectors.toList());
    }
    @Operation(summary = "Get product by ID", description = "Finds a product by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<AccommodationDto> findById(@PathVariable Long id) {
        return accommodationService.findById(id)
                .map(AccommodationDto::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add a new product",
            description = "Creates a new product based on the given ProductDto."
    )
    @PostMapping("/add")
    public ResponseEntity<AccommodationDto> save(@RequestBody AccommodationDto createProductDto) {
        if(hostService.findById(createProductDto.host()).isPresent()){
            return accommodationService.save(createProductDto.toAccommodation(hostService.findById(createProductDto.host()).get()))
                    .map(AccommodationDto::from)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }

        return  ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Update an existing product", description = "Updates a product by ID using ProductDto."
    )
    @PutMapping("/edit/{id}")
    public ResponseEntity<AccommodationDto> update(
            @PathVariable Long id,
            @RequestBody AccommodationDto product
    ) {
        return accommodationService.edit(id, product)
                .map(AccommodationDto::from)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a product", description = "Deletes a product by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (accommodationService.findById(id).isPresent()) {
            accommodationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

