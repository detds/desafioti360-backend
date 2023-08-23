package com.dennist.desafioti360backend.repositories;

import com.dennist.desafioti360backend.models.Matricula;
import com.dennist.desafioti360backend.models.MatriculaPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, MatriculaPK> {
    Matricula findByIdAlunoMatriculaAndIdCursoCodigo(Long alunoMatricula, Long cursoCodigo);
}
