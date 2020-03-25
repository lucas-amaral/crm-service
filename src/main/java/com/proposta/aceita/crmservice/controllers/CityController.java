package com.proposta.aceita.crmservice.controllers;

import com.proposta.aceita.crmservice.entities.req.CityRequestBody;
import com.proposta.aceita.crmservice.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        return cityService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(cityService.getList());
    }

    @PostMapping
    public ResponseEntity<?> post(@Validated @RequestBody CityRequestBody body) {
        cityService.create(body);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> put(@Validated @RequestBody CityRequestBody body) {
        cityService.update(body);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        cityService.delete(id);
        return ResponseEntity.ok().build();
    }
}
