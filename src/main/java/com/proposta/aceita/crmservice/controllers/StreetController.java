package com.proposta.aceita.crmservice.controllers;

import com.proposta.aceita.crmservice.services.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/streets")
public class StreetController {

    private final StreetService streetService;

    @Autowired
    public StreetController(StreetService streetService) {
        this.streetService = streetService;
    }

    @GetMapping("/{zipCode:\\d{5}-\\d{3}}")
    public ResponseEntity<?> get(@PathVariable String zipCode) {
        return streetService.getByZipCode(zipCode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return ResponseEntity.ok(streetService.list(page, limit));
    }
}
