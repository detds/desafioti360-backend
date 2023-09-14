package com.dennist.desafioti360backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/health")
public class HealthCheckController {

    @GetMapping
    public ResponseEntity<String> verificarStatus() {
        return ResponseEntity.ok("A aplicação está funcionando!");
    }

}
