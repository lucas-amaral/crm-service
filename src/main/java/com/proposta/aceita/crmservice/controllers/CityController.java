package com.proposta.aceita.crmservice.controllers;

import com.proposta.aceita.crmservice.entities.enums.State;
import com.proposta.aceita.crmservice.entities.req.CityRequestBody;
import com.proposta.aceita.crmservice.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestParam("stateCode") State state) {
        return ResponseEntity.ok(cityService.getByState(state));
    }

    @PostMapping
    public ResponseEntity<?> post(@Validated @RequestBody CityRequestBody body) {
        return cityService.save(body)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PutMapping
    public ResponseEntity<?> put(@Validated @RequestBody CityRequestBody body) {
        return cityService.save(body)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        cityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
