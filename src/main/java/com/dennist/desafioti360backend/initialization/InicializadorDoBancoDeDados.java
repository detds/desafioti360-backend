package com.dennist.desafioti360backend.initialization;

import com.dennist.desafioti360backend.models.Aluno;
import com.dennist.desafioti360backend.repositories.AlunoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class InicializadorDoBancoDeDados implements CommandLineRunner {

    private final AlunoRepository alunoRepository;

    public InicializadorDoBancoDeDados(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Limpando banco de dados
        alunoRepository.deleteAll();

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
        
        // salvar dados
        alunoRepository.saveAll(Arrays.asList(aluno1, aluno2));
    }
}
