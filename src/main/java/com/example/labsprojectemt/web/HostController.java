package com.example.labsprojectemt.web;


import com.example.labsprojectemt.domain.dto.CreateHostDto;
import com.example.labsprojectemt.domain.dto.DisplayHostDto;
import com.example.labsprojectemt.service.application.CountryApplicationService;
import com.example.labsprojectemt.service.application.HostApplicationService;
import com.example.labsprojectemt.service.domain.CountryService;
import com.example.labsprojectemt.service.domain.HostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/host")
@Tag(name = "Host API", description = "Endpoints for managing Hosts") // OpenAPI tag
public class HostController {

    private final HostApplicationService hostService;
    private final CountryApplicationService countryService;

    public HostController(HostApplicationService hostService, CountryApplicationService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }
    @Operation(summary = "Get all products", description = "Retrieves a list of all available products.")
    @GetMapping
    public List<DisplayHostDto> findAll() {
        return hostService.findAll();
    }

    @Operation(summary = "Get product by ID", description = "Finds a product by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayHostDto> findById(@PathVariable Long id) {
        return hostService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add a new product",
            description = "Creates a new product based on the given ProductDto."
    )
    @PostMapping("/add")
    public ResponseEntity<DisplayHostDto> save(@RequestBody CreateHostDto createProductDto) {
        return hostService.save(createProductDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a product", description = "Deletes a product by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (hostService.findById(id).isPresent()) {
            hostService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
