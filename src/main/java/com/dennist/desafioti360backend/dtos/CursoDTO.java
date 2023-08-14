package com.dennist.desafioti360backend.dtos;

import com.dennist.desafioti360backend.models.Aluno;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

public class CursoDTO {

    private Long codigo;
    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    @JsonIgnoreProperties({"cursos"})
    private Set<Aluno> alunos = new HashSet<>();

    public CursoDTO(Long codigo, String nome, Set<Aluno> alunos) {
        this.codigo = codigo;
        this.nome = nome;
        this.alunos = alunos;
    }

    public Long getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }
}
