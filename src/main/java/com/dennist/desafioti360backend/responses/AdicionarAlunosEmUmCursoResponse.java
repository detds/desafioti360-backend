package com.dennist.desafioti360backend.responses;

import java.util.Set;

public class AdicionarAlunosEmUmCursoResponse {

    private String msg;
    private String nomeCurso;
    private Set<Long> matriculasAdicionadas;

    public AdicionarAlunosEmUmCursoResponse(String msg,
                                            String nomeCurso,
                                            Set<Long> matriculasAdicionadas) {
        this.msg = msg;
        this.nomeCurso = nomeCurso;
        this.matriculasAdicionadas = matriculasAdicionadas;
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

    public Set<Long> getMatriculasAdicionadas() {
        return matriculasAdicionadas;
    }

    public void setMatriculasAdicionadas(Set<Long> matriculasAdicionadas) {
        this.matriculasAdicionadas = matriculasAdicionadas;
    }
}
