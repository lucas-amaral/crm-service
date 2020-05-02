package com.proposta.aceita.crmservice.controllers.internal;

import com.proposta.aceita.crmservice.services.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("internal/interests/users")
public class InternalInterestController {

    private final InterestService interestService;

    @Autowired
    public InternalInterestController(InterestService interestService) {
        this.interestService = interestService;
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        return interestService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
