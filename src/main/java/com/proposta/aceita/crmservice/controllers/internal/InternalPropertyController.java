package com.proposta.aceita.crmservice.controllers.internal;

import com.proposta.aceita.crmservice.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("internal/properties")
public class InternalPropertyController {

    private final PropertyService propertyService;

    @Autowired
    public InternalPropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        return propertyService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
