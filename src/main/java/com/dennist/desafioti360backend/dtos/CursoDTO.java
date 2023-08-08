package com.dennist.desafioti360backend.dtos;

import jakarta.validation.constraints.NotBlank;

public class CursoDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
