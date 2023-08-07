package com.dennist.desafioti360backend.controllers;

import com.dennist.desafioti360backend.models.Curso;
import com.dennist.desafioti360backend.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cursos")
public class CursoController {

    @Autowired
    private CursoService service;

    @GetMapping
    public ResponseEntity<List<Curso>> findAll() {
        List<Curso> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

}
