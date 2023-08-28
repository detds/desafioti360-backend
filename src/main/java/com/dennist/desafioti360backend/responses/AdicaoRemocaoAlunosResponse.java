package com.dennist.desafioti360backend.responses;

import java.util.Set;

public class AdicaoRemocaoAlunosResponse {

    private String msg;
    private String nomeCurso;
    private Set<Long> matriculas;

    public AdicaoRemocaoAlunosResponse(String msg,
                                       String nomeCurso,
                                       Set<Long> matriculas) {
        this.msg = msg;
        this.nomeCurso = nomeCurso;
        this.matriculas = matriculas;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Set<Long> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(Set<Long> matriculas) {
        this.matriculas = matriculas;
    }
}
