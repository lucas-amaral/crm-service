package com.proposta.aceita.crmservice.controllers;

import com.proposta.aceita.crmservice.entities.req.AddressRequestBody;
import com.proposta.aceita.crmservice.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

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
                .orElse(ResponseEntity.status(NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(addressService.list());
    }

    @PostMapping
    public ResponseEntity<?> post(@Validated @RequestBody AddressRequestBody body) {
        addressService.save(body);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> put(@Validated @RequestBody AddressRequestBody body) {
        addressService.save(body);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        addressService.delete(id);
        return ResponseEntity.ok().build();
    }
}
