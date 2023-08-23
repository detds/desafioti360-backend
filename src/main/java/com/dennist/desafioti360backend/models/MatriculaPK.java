package com.dennist.desafioti360backend.models;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class MatriculaPK implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "aluno_matricula")
    @JsonIdentityReference(alwaysAsId = true)
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "curso_codigo")
    @JsonIdentityReference(alwaysAsId = true)
    private Curso curso;

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatriculaPK that)) return false;

        if (!getAluno().equals(that.getAluno())) return false;
        return getCurso().equals(that.getCurso());
    }

    @Override
    public int hashCode() {
        int result = getAluno().hashCode();
        result = 31 * result + getCurso().hashCode();
        return result;
    }
}
