package com.dennist.desafioti360backend.initialization;

import com.dennist.desafioti360backend.models.Aluno;
import com.dennist.desafioti360backend.models.Curso;
import com.dennist.desafioti360backend.models.Matricula;
import com.dennist.desafioti360backend.repositories.AlunoRepository;
import com.dennist.desafioti360backend.repositories.CursoRepository;
import com.dennist.desafioti360backend.repositories.MatriculaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

@Component
public class InicializadorDoBancoDeDados implements CommandLineRunner {

    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;

    private final MatriculaRepository matriculaRepository;

    public InicializadorDoBancoDeDados(AlunoRepository alunoRepository, CursoRepository cursoRepository, MatriculaRepository matriculaRepository) {
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
        this.matriculaRepository = matriculaRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Limpando banco de dados
        alunoRepository.deleteAll();
        cursoRepository.deleteAll();

        // criando novos alunos
        Aluno aluno1 = new Aluno(
                null,
                "Anderson",
                20,
                "anderson@email.com");
        Aluno aluno2 = new Aluno(
                null,
                "Mariano",
                21,
                "mariano@email.com");

        // criando novos cursos
        Curso curso1 = new Curso(
                null,
                "Curso de Inglês"
        );
        Curso curso2 = new Curso(
                null,
                "Curso de Piano"
        );


        // salvar dados
        alunoRepository.saveAll(Arrays.asList(aluno1, aluno2));
        cursoRepository.saveAll(Arrays.asList(curso1, curso2));        alunoRepository.saveAll(Arrays.asList(aluno1, aluno2));
        cursoRepository.saveAll(Arrays.asList(curso1, curso2));

        // matricular alunos nos cursos
        Matricula matricula1 = new Matricula(aluno1, curso1, LocalDate.of(2023, Month.AUGUST, 10));
        Matricula matricula2 = new Matricula(aluno1, curso2, LocalDate.of(2023, Month.AUGUST, 10));
        Matricula matricula3 = new Matricula(aluno2, curso1, LocalDate.of(2023, Month.AUGUST, 11));

        curso1.getMatriculas().addAll(Arrays.asList(matricula1, matricula3));
        curso2.getMatriculas().addAll(Arrays.asList(matricula2));

        aluno1.getMatriculas().addAll(Arrays.asList(matricula1, matricula2));
        aluno2.getMatriculas().addAll(Arrays.asList(matricula3));

        // salvar dados
        matriculaRepository.saveAll(Arrays.asList(matricula1, matricula2, matricula3));
    }
}
