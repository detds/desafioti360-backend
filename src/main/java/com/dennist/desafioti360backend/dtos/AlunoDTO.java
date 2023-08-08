package com.dennist.desafioti360backend.dtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class AlunoDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    @Range(min = 10, max = 120, message = "A idade deve estar entre 10 e 120 anos")
    private int idade;
    @NotBlank(message = "O email é obrigatório")
    private String email;

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
}
