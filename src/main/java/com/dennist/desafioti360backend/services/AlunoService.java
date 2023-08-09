package com.dennist.desafioti360backend.services;

import com.dennist.desafioti360backend.dtos.AlunoDTO;
import com.dennist.desafioti360backend.models.Aluno;
import com.dennist.desafioti360backend.repositories.AlunoRepository;
import com.dennist.desafioti360backend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public List<Aluno> findAll() {
        return repository.findAll();
    }

    public Aluno find(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public Aluno save(AlunoDTO alunoDTO) {
        Aluno obj = new Aluno();
        BeanUtils.copyProperties(alunoDTO, obj);
        return repository.save(obj);
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public void delete(Long id) {
        find(id);
        repository.deleteById(id);
    }
}
