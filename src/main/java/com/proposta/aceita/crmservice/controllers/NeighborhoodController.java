package com.proposta.aceita.crmservice.controllers;

import com.proposta.aceita.crmservice.services.NeighborhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> getByCity(@RequestParam("cityId") Integer cityId) {
        return ResponseEntity.ok(neighborhoodService.getByCity(cityId));
    }
}
