package com.dennist.desafioti360backend.dtos;

import com.dennist.desafioti360backend.models.Curso;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import java.util.HashSet;
import java.util.Set;

public class AlunoDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    @Range(min = 10, max = 120, message = "A idade deve estar entre 10 e 120 anos")
    private int idade;
    @NotBlank(message = "O email é obrigatório")
    private String email;
    private Set<Curso> cursos = new HashSet<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
    }
}
