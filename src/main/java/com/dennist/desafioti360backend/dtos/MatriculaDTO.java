package com.dennist.desafioti360backend.dtos;

import com.dennist.desafioti360backend.models.MatriculaPK;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class MatriculaDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private MatriculaPK id;

    @NotBlank(message = "Data é obrigatória")
    private LocalDate dataMatriculaCurso;

    public MatriculaDTO(MatriculaPK id, LocalDate dataMatriculaCurso) {
        this.id = id;
        this.dataMatriculaCurso = dataMatriculaCurso;
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

}
