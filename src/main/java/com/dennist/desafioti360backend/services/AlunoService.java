package com.dennist.desafioti360backend.services;

import com.dennist.desafioti360backend.dtos.AlunoDTO;
import com.dennist.desafioti360backend.models.Aluno;
import com.dennist.desafioti360backend.repositories.AlunoRepository;
import com.dennist.desafioti360backend.services.exceptions.DataIntegrityException;
import com.dennist.desafioti360backend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public List<Aluno> listarTodos() {
        return repository.findAll();
    }

    public Aluno buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Aluno> buscarTodosPorId(Set<Long> ids) {
        return repository.findAllById(ids);
    }

    public Aluno salvar(AlunoDTO alunoDTO) {
        Aluno obj = new Aluno();
        BeanUtils.copyProperties(alunoDTO, obj);
        return repository.save(obj);
    }

    public boolean emailJaCadastrado(String email) {
        return repository.existsByEmail(email);
    }

    public void detelarPorId(Long id) {
        Aluno alunoEncontrado = buscarPorId(id);

        if (alunoEncontrado.getCursos().isEmpty()) {
            repository.deleteById(id);
        } else {
            throw new DataIntegrityException("O aluno possui matrícula em um ou mais cursos e não pode ser excluído.");
        }
    }

    public Aluno atualizar(Long id, AlunoDTO alunoDTO) {
        Aluno entity = buscarPorId(id);
        BeanUtils.copyProperties(alunoDTO, entity);
        return repository.save(entity);
    }
}
