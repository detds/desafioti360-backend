package com.dennist.desafioti360backend.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class CursoDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long codigo;
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    public CursoDTO(Long codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
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

}
