package com.proposta.aceita.crmservice.controllers;

import com.proposta.aceita.crmservice.entities.PropertyImage;
import com.proposta.aceita.crmservice.entities.req.AddPropertyRequestBody;
import com.proposta.aceita.crmservice.entities.req.EditPropertyRequestBody;
import com.proposta.aceita.crmservice.services.PropertyImageService;
import com.proposta.aceita.crmservice.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;
    private final PropertyImageService propertyImageService;

    @Autowired
    public PropertyController(PropertyService propertyService, PropertyImageService propertyImageService) {
        this.propertyService = propertyService;
        this.propertyImageService = propertyImageService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        return propertyService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> getByUser(@RequestParam("username") String username) {
        return ResponseEntity.ok(propertyService.getByUser(username));
    }

    @PostMapping
    public ResponseEntity<?> post(@Validated @RequestBody AddPropertyRequestBody body) {
        return propertyService.save(body)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(INTERNAL_SERVER_ERROR).build());
    }

    @PutMapping
    public ResponseEntity<?> put(@Validated @RequestBody EditPropertyRequestBody body) {
        return propertyService.save(body)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(INTERNAL_SERVER_ERROR).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        propertyService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/images")
    public ResponseEntity<?> getImages(@PathVariable Integer id) {
        return propertyImageService.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/images")
    public ResponseEntity<?> postImages(@Validated @RequestBody List<PropertyImage> body) {
        return propertyImageService.save(body)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/images/{imageId}")
    public ResponseEntity<?> delete(@PathVariable String imageId) {
        propertyImageService.delete(imageId);
        return ResponseEntity.noContent().build();
    }
}
