package com.proposta.aceita.crmservice.controllers;

import com.proposta.aceita.crmservice.services.NegotiationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/negotiations")
public class NegotiationController {

    private final NegotiationService negotiationService;

    @Autowired
    public NegotiationController(NegotiationService negotiationService) {
        this.negotiationService = negotiationService;
    }

    @GetMapping("/sales")
    public ResponseEntity<?> getBySale(@RequestParam("username") String username) {
        return ResponseEntity.ok(negotiationService.getForSeller(username));
    }

    @GetMapping("/interests")
    public ResponseEntity<?> getByInterest(@RequestParam("username") String username) {
        return ResponseEntity.ok(negotiationService.getForBuyer(username));
    }

    @GetMapping("/sales/{saleId}")
    public ResponseEntity<?> getBySale(@PathVariable Integer saleId) {
        return ResponseEntity.ok(negotiationService.getBySale(saleId));
    }

    @GetMapping("/interests/{interestId}")
    public ResponseEntity<?> getByInterest(@PathVariable Integer interestId) {
        return ResponseEntity.ok(negotiationService.getByInterest(interestId));
    }

    @PutMapping("/{id}/approved-by-seller")
    public ResponseEntity<?> approvedBySeller(@PathVariable String id) {
        negotiationService.approvedBySeller(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/approved-by-buyer")
    public ResponseEntity<?> approvedByBuyer(@PathVariable String id) {
        negotiationService.approvedByBuyer(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/reproved-by-seller")
    public ResponseEntity<?> reprovedBySeller(@PathVariable String id) {
        negotiationService.reprovedBySeller(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/reproved-by-buyer")
    public ResponseEntity<?> reprovedByBuyer(@PathVariable String id) {
        negotiationService.reprovedByBuyer(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        negotiationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
