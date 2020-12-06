package com.proposta.aceita.crmservice.controllers;

import com.proposta.aceita.crmservice.entities.req.AddUserRequestBody;
import com.proposta.aceita.crmservice.entities.req.EditUserRequestBody;
import com.proposta.aceita.crmservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> get(@PathVariable String username) {
        return userService.getById(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(userService.list());
    }

    @PostMapping
    public ResponseEntity<?> post(@Validated @RequestBody AddUserRequestBody body) {
        return userService.create(body)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(INTERNAL_SERVER_ERROR).build());
    }

    @PutMapping
    public ResponseEntity<?> put(@Validated @RequestBody EditUserRequestBody body) {
        return userService.save(body)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(INTERNAL_SERVER_ERROR).build());
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> delete(@PathVariable String username) {
        userService.delete(username);
        return ResponseEntity.noContent().build();
    }
}
