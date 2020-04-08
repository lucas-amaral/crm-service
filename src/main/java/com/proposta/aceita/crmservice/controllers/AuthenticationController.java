package com.proposta.aceita.crmservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
public class AuthenticationController {

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(HttpServletResponse response) {
        response.setHeader("Access-Control-Expose-Headers", "X-Auth-Token");
        return ResponseEntity.ok().build();
    }

}
