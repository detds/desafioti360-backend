package com.dennist.desafioti360backend.controllers;

import com.dennist.desafioti360backend.dtos.CursoDTO;
import com.dennist.desafioti360backend.models.Curso;
import com.dennist.desafioti360backend.services.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<Curso> find(@PathVariable(value = "id") Long id) {
        Curso obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid CursoDTO cursoDTO) {

        Curso obj = service.save(cursoDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(obj.getCodigo())
                .toUri();

        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id,
                                    @RequestBody @Valid CursoDTO cursoDTO) {
        Curso obj = service.update(id, cursoDTO);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
