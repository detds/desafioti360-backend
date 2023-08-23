package com.dennist.desafioti360backend.services;

import com.dennist.desafioti360backend.models.Aluno;
import com.dennist.desafioti360backend.models.Curso;
import com.dennist.desafioti360backend.models.Matricula;
import com.dennist.desafioti360backend.repositories.MatriculaRepository;
import com.dennist.desafioti360backend.services.exceptions.ObjectAlreadyExistsException;
import com.dennist.desafioti360backend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;
    @Autowired
    private AlunoService alunoService;
    @Autowired
    private CursoService cursoService;

    @Transactional
    public Matricula adicionarAlunoACurso(Long alunoId, Long cursoId) {

        Aluno alunoEntity = alunoService.find(alunoId);
        Curso cursoEntity = cursoService.find(cursoId);

        Matricula novaMatriculaAlunoCurso = new Matricula(alunoEntity, cursoEntity, LocalDate.now());

        if (matriculaRepository.findById(novaMatriculaAlunoCurso.getId()).isPresent()) {
            throw new ObjectAlreadyExistsException("Aluno já possui matrícula neste curso");
        }

        cursoEntity.getMatriculas().add(novaMatriculaAlunoCurso);
        alunoEntity.getMatriculas().add(novaMatriculaAlunoCurso);

        return matriculaRepository.save(novaMatriculaAlunoCurso);
    }

    @Transactional
    public void removerAlunoDoCurso(Long alunoId, Long cursoId) {

        Matricula matriculaObj = matriculaRepository.findByIdAlunoMatriculaAndIdCursoCodigo(alunoId, cursoId);

        if (matriculaObj == null) {
            throw new ObjectNotFoundException("Matrícula do aluno " + alunoId + " no curso " + cursoId + " não encontrada");
        }

        matriculaObj.getAluno().getMatriculas().remove(matriculaObj);
        matriculaObj.getCurso().getMatriculas().remove(matriculaObj);

        matriculaRepository.delete(matriculaObj);
    }
}
