package com.proposta.aceita.crmservice.controllers;

import com.proposta.aceita.crmservice.entities.req.UserRequestBody;
import com.proposta.aceita.crmservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        return userService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(userService.list());
    }

    @PostMapping
    public ResponseEntity<?> post(@Validated @RequestBody UserRequestBody body) {
        userService.save(body);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> put(@Validated @RequestBody UserRequestBody body) {
        userService.save(body);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
