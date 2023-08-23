package com.dennist.desafioti360backend.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Matricula implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private MatriculaPK id = new MatriculaPK();

    private LocalDate dataMatriculaCurso;

    public Matricula() {
    }

    public Matricula(Aluno aluno, Curso curso, LocalDate dataMatriculaCurso) {
        this.id.setAluno(aluno);
        this.id.setCurso(curso);
        this.dataMatriculaCurso = dataMatriculaCurso;
    }

    @JsonIgnore
    public Aluno getAluno() {
        return this.id.getAluno();
    }

    @JsonIgnoreProperties("matriculas")
    public Curso getCurso() {
        return this.id.getCurso();
    }

    public MatriculaPK getId() {
        return id;
    }

    public LocalDate getDataMatriculaCurso() {
        return dataMatriculaCurso;
    }

    public void setDataMatriculaCurso(LocalDate dataMatriculaCurso) {
        this.dataMatriculaCurso = dataMatriculaCurso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matricula matricula)) return false;

        return id.equals(matricula.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
