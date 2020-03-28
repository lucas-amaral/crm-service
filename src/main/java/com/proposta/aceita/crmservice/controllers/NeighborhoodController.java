package com.proposta.aceita.crmservice.controllers;

import com.proposta.aceita.crmservice.services.NeighborhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/neighborhoods")
public class NeighborhoodController {

    private final NeighborhoodService neighborhoodService;

    @Autowired
    public NeighborhoodController(NeighborhoodService neighborhoodService) {
        this.neighborhoodService = neighborhoodService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        return neighborhoodService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(neighborhoodService.list());
    }
}
