package com.proposta.aceita.crmservice.controllers;

import com.proposta.aceita.crmservice.entities.BarterImage;
import com.proposta.aceita.crmservice.entities.req.AddInterestRequestBody;
import com.proposta.aceita.crmservice.entities.req.EditInterestRequestBody;
import com.proposta.aceita.crmservice.services.BarterImageService;
import com.proposta.aceita.crmservice.services.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/interests")
public class InterestController {

    private final InterestService interestService;
    private final BarterImageService barterImageService;

    @Autowired
    public InterestController(InterestService interestService, BarterImageService barterImageService) {
        this.interestService = interestService;
        this.barterImageService = barterImageService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        return interestService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> getByUser(@RequestParam("username") String username) {
        return interestService.getByUser(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> post(@Validated @RequestBody AddInterestRequestBody body) {
        return interestService.save(body)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(INTERNAL_SERVER_ERROR).build());
    }

    @PutMapping
    public ResponseEntity<?> put(@Validated @RequestBody EditInterestRequestBody body) {
        return interestService.save(body)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(INTERNAL_SERVER_ERROR).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        interestService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/barters/{barterId}/images")
    public ResponseEntity<?> getImages(@PathVariable Integer barterId) {
        return barterImageService.get(barterId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/barters/images")
    public ResponseEntity<?> postImages(@Validated @RequestBody List<BarterImage> body) {
        return barterImageService.save(body)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/barters/images/{imageId}")
    public ResponseEntity<?> delete(@PathVariable String imageId) {
        barterImageService.delete(imageId);
        return ResponseEntity.noContent().build();
    }
}
