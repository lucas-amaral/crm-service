package com.proposta.aceita.crmservice.controllers;

import com.proposta.aceita.crmservice.services.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/streets")
public class StreetController {

    private final StreetService streetService;

    @Autowired
    public StreetController(StreetService streetService) {
        this.streetService = streetService;
    }

    @GetMapping("/{zipCode}")
    public ResponseEntity<?> get(@PathVariable String zipCode) {
        return streetService.getByZipCode(zipCode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestParam("neighborhoodId") Integer neighborhoodId,
                                 @RequestParam("page") Integer page,
                                 @RequestParam("limit") Integer limit) {
        return ResponseEntity.ok(streetService.list(neighborhoodId, page, limit));
    }
}
