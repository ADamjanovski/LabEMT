package com.example.labsprojectemt.web;

import com.example.labsprojectemt.domain.dto.CreateAccommodationDto;
import com.example.labsprojectemt.domain.dto.DisplayAccommodationDto;
import com.example.labsprojectemt.domain.views.AccommodationsPerHostView;
import com.example.labsprojectemt.service.application.AccommodationApplicationService;
import com.example.labsprojectemt.service.domain.AccommodationService;
import com.example.labsprojectemt.service.domain.HostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accommodation")
@Tag(name = "Accommodation API", description = "Endpoints for managing accommodations")
public class AccommodationController {
    private final AccommodationApplicationService accommodationService;
    private final HostService hostService;

    public AccommodationController(AccommodationApplicationService accommodationService, HostService hostService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
    }
    @Operation(summary = "Get all accommodation", description = "Retrieves a list of all available accommodation.")
    @GetMapping
    public List<DisplayAccommodationDto> findAll() {
        return accommodationService.findAll();
    }

    @Operation(summary = "Get all available accommodation", description = "Retrieves a list of all available accommodation.")
    @GetMapping("/availableAccommodation")
    public List<DisplayAccommodationDto> findAvailable(@RequestParam
                                                        @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                        LocalDate startDate,
                                                      @RequestParam
                                                        @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                    LocalDate endDate
    ) {
        return accommodationService.availableAccommodation(startDate,endDate);
    }

    @Operation(summary = "Get num accommodations per host", description = "Retrieves a list of num accommodations by hosts.")
    @GetMapping("/by-host")
    public List<AccommodationsPerHostView> findNumAccommodationsByHost(
    ) {
        return accommodationService.findAllAccommodationsByHost();
    }
    @Operation(summary = "Get product by ID", description = "Finds a product by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayAccommodationDto> findById(@PathVariable Long id) {
        return accommodationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add a new product",
            description = "Creates a new product based on the given ProductDto."
    )
    @PostMapping("/add")
    public ResponseEntity<DisplayAccommodationDto> save(@RequestBody CreateAccommodationDto createProductDto) {
        if(hostService.findById(createProductDto.host()).isPresent()){
            return accommodationService.save(createProductDto)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }

        return  ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Update an existing product", description = "Updates a product by ID using ProductDto."
    )
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayAccommodationDto> update(
            @PathVariable Long id,
            @RequestBody CreateAccommodationDto product
    ) {
        return accommodationService.edit(id, product)
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

