package com.dennist.desafioti360backend.initialization;

import com.dennist.desafioti360backend.models.Aluno;
import com.dennist.desafioti360backend.models.Curso;
import com.dennist.desafioti360backend.repositories.AlunoRepository;
import com.dennist.desafioti360backend.repositories.CursoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class InicializadorDoBancoDeDados implements CommandLineRunner {

    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;

    public InicializadorDoBancoDeDados(AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
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
        cursoRepository.saveAll(Arrays.asList(curso1, curso2));

        // Adicionar alunos aos cursos
        curso1.getAlunos().addAll(Arrays.asList(aluno1, aluno2));
        curso2.getAlunos().add(aluno2);

        // salvar dados
        cursoRepository.saveAll(Arrays.asList(curso1, curso2));
    }
}
