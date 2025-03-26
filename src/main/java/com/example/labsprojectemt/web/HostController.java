package com.example.labsprojectemt.web;


import com.example.labsprojectemt.model.dto.HostDto;
import com.example.labsprojectemt.service.CountryService;
import com.example.labsprojectemt.service.HostService;
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

    private final HostService hostService;
    private final CountryService countryService;

    public HostController(HostService hostService, CountryService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }
    @Operation(summary = "Get all products", description = "Retrieves a list of all available products.")
    @GetMapping
    public List<HostDto> findAll() {
        return hostService.findAll().stream().map(HostDto::from).collect(Collectors.toList());
    }

    @Operation(summary = "Get product by ID", description = "Finds a product by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<HostDto> findById(@PathVariable Long id) {
        return hostService.findById(id)
                .map(HostDto::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add a new product",
            description = "Creates a new product based on the given ProductDto."
    )
    @PostMapping("/add")
    public ResponseEntity<HostDto> save(@RequestBody HostDto createProductDto) {
        return hostService.save(createProductDto.toHost(countryService.findById(createProductDto.country()).orElseThrow()))
                .map(HostDto::from)
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
