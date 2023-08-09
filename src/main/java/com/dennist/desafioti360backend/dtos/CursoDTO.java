package com.dennist.desafioti360backend.dtos;

import com.dennist.desafioti360backend.models.Aluno;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

public class CursoDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    private Set<Aluno> alunos = new HashSet<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }
}
