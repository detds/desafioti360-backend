package com.dennist.desafioti360backend.controllers;

import com.dennist.desafioti360backend.models.Aluno;
import com.dennist.desafioti360backend.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @GetMapping
    public ResponseEntity<List<Aluno>> findAll() {
        List<Aluno> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

}
