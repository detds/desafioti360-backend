package com.dennist.desafioti360backend.services;

import com.dennist.desafioti360backend.dtos.CursoDTO;
import com.dennist.desafioti360backend.models.Curso;
import com.dennist.desafioti360backend.repositories.CursoRepository;
import com.dennist.desafioti360backend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
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

    public Curso find(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public Curso save(CursoDTO cursoDTO) {

        Curso obj = new Curso();
        BeanUtils.copyProperties(cursoDTO, obj);

        return repository.save(obj);
    }
}
