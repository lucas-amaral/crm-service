package com.proposta.aceita.crmservice.controllers;

import com.proposta.aceita.crmservice.entities.BarterImage;
import com.proposta.aceita.crmservice.entities.req.AddBarterRequestBody;
import com.proposta.aceita.crmservice.entities.req.EditBarterRequestBody;
import com.proposta.aceita.crmservice.services.BarterImageService;
import com.proposta.aceita.crmservice.services.BarterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/barters")
public class BarterController {

    private final BarterService barterService;
    private final BarterImageService barterImageService;

    @Autowired
    public BarterController(BarterService barterService, BarterImageService barterImageService) {
        this.barterService = barterService;
        this.barterImageService = barterImageService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        return barterService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> getByInterest(@RequestParam("interestId") Integer interestId) {
        return ResponseEntity.ok(barterService.getByInterest(interestId));
    }

    @PostMapping
    public ResponseEntity<?> post(@Validated @RequestBody AddBarterRequestBody body) {
        return barterService.save(body)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(INTERNAL_SERVER_ERROR).build());
    }

    @PutMapping
    public ResponseEntity<?> put(@Validated @RequestBody EditBarterRequestBody body) {
        return barterService.save(body)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(INTERNAL_SERVER_ERROR).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        barterService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}/images")
    public ResponseEntity<?> getImages(@PathVariable Integer id) {
        return barterImageService.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/images")
    public ResponseEntity<?> postImages(@Validated @RequestBody List<BarterImage> body) {
        return barterImageService.save(body)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/images/{imageId}")
    public ResponseEntity<?> delete(@PathVariable String imageId) {
        barterImageService.delete(imageId);
        return ResponseEntity.noContent().build();
    }
}
