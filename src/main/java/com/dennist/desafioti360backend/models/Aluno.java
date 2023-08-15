package com.dennist.desafioti360backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_aluno")
public class Aluno implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matricula;
    private String nome;
    private int idade;
    private String email;

    @ManyToMany(mappedBy = "alunos")
    @JsonIgnoreProperties({"alunos"})
    private Set<Curso> cursos = new HashSet<>();

    public Aluno() {
    }

    public Aluno(Long matricula, String nome, int idade, String email) {
        this.matricula = matricula;
        this.nome = nome;
        this.idade = idade;
        this.email = email;
    }

    public long getMatricula() {
        return matricula;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Aluno aluno = (Aluno) o;

        return matricula.equals(aluno.matricula);
    }

    @Override
    public int hashCode() {
        return matricula.hashCode();
    }
}
