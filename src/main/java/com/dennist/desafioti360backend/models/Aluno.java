package com.dennist.desafioti360backend.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_aluno")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "matricula")
public class Aluno implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matricula;
    private String nome;
    private int idade;
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "id.aluno")
    private Set<Matricula> matriculas = new HashSet<>();

    public Aluno() {
    }

    public Aluno(Long matricula, String nome, int idade, String email) {
        this.matricula = matricula;
        this.nome = nome;
        this.idade = idade;
        this.email = email;
    }

    @JsonIgnore
    public Set<Curso> getCursos () {
        Set<Curso> lista = new HashSet<>();
        for (Matricula x : matriculas) {
            lista.add(x.getCurso());
        }
        return lista;
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

    @JsonIgnore
    public Set<Matricula> getMatriculas() {
        return matriculas;
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
