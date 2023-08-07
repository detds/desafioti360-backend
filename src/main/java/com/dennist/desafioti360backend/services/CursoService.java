package com.dennist.desafioti360backend.services;

import com.dennist.desafioti360backend.models.Curso;
import com.dennist.desafioti360backend.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;

    public List<Curso> findAll() {
        return repository.findAll();
    }
}
