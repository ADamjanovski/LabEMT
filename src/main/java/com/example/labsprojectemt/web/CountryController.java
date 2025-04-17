package com.example.labsprojectemt.web;

import com.example.labsprojectemt.domain.Country;
import com.example.labsprojectemt.domain.dto.CreateCountryDto;
import com.example.labsprojectemt.domain.dto.DisplayCountryDto;
import com.example.labsprojectemt.service.application.CountryApplicationService;
import com.example.labsprojectemt.service.domain.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@Tag(name = "Country API", description = "Endpoints for managing Countries") // OpenAPI tag
public class CountryController {
    private final CountryApplicationService countryService;

    public CountryController(CountryApplicationService countryService) {
        this.countryService = countryService;
    }

    @Operation(summary = "Get all countries", description = "Retrieves a list of all available categories.")
    @GetMapping
    public List<DisplayCountryDto> findAll() {
        return countryService.findAll();
    }

    @Operation(summary = "Get category by ID", description = "Finds a category by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayCountryDto> findById(@PathVariable Long id) {
        return countryService.findById(id)
                .map(category -> ResponseEntity.ok().body(category))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new country", description = "Creates a new country.")
    @PostMapping("/add")
    public ResponseEntity<DisplayCountryDto> save(@RequestBody CreateCountryDto createCountryDto) {
        return countryService.save(createCountryDto)
                .map(category -> ResponseEntity.ok().body(category))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Delete a country", description = "Deletes a country by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (countryService.findById(id).isPresent()) {
            countryService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
