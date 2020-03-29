package com.proposta.aceita.crmservice.controllers;

import com.proposta.aceita.crmservice.entities.req.AddressRequestBody;
import com.proposta.aceita.crmservice.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        return addressService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(addressService.list());
    }

    @PostMapping
    public ResponseEntity<?> post(@Validated @RequestBody AddressRequestBody body) {
        return addressService.save(body)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PutMapping
    public ResponseEntity<?> put(@Validated @RequestBody AddressRequestBody body) {
        return addressService.save(body)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
