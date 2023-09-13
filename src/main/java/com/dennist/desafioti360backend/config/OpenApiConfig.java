package com.dennist.desafioti360backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(servers = @Server(url = "https://desafio-backend-api.fly.dev"),
        info = @Info(
        description = "Microsserviço para Cadastro e Consulta de Alunos e Cursos",
        title = "Desafio TI360 - Backend API",
        version = "1.0"))
public class OpenApiConfig {
}
