package com.dennist.desafioti360backend.dtos;

import com.dennist.desafioti360backend.models.Aluno;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

public class CursoDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long codigo;
    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonIgnoreProperties({"cursos"})
    private Set<Aluno> alunos = new HashSet<>();

    public CursoDTO() {
    }

    public CursoDTO(Long codigo, String nome, Set<Aluno> alunos) {
        this.codigo = codigo;
        this.nome = nome;
        this.alunos = alunos;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }
}
