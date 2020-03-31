package com.proposta.aceita.crmservice.controllers;

import com.proposta.aceita.crmservice.entities.req.AddSaleRequestBody;
import com.proposta.aceita.crmservice.entities.req.EditSaleRequestBody;
import com.proposta.aceita.crmservice.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        return saleService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> getByProperty(@RequestParam("propertyId") Integer propertyId) {
        return saleService.getByProperty(propertyId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> post(@Validated @RequestBody AddSaleRequestBody body) {
        return saleService.save(body)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(INTERNAL_SERVER_ERROR).build());
    }

    @PutMapping
    public ResponseEntity<?> put(@Validated @RequestBody EditSaleRequestBody body) {
        return saleService.save(body)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(INTERNAL_SERVER_ERROR).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        saleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
