package com.dennist.desafioti360backend.controllers;

import com.dennist.desafioti360backend.dtos.AlunoDTO;
import com.dennist.desafioti360backend.models.Aluno;
import com.dennist.desafioti360backend.services.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/alunos")
@Tag(name = "Aluno", description = "Aluno API")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @GetMapping
    @Operation(summary = "Obter todos os alunos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")})
    public ResponseEntity<List<AlunoDTO>> findAll() {
        List<Aluno> alunoList = service.findAll();

        List<AlunoDTO> alunoDTOList = alunoList.stream()
                .map(aluno -> new AlunoDTO(aluno.getMatricula(), aluno.getNome(), aluno.getIdade(), aluno.getEmail(), aluno.getCursos()))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(alunoDTOList);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Obter aluno pelo número de matrícula")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida"),
            @ApiResponse(responseCode = "404", description = "ERRO - Aluno não encontrado. A resposta de erro incluirá informações sobre o status, a mensagem e o timestamp", content = @Content),
            @ApiResponse(responseCode = "500", description = "ERRO - Erro inesperado", content = @Content)})
    public ResponseEntity<AlunoDTO> find(@PathVariable(value = "id") Long id) {
        Aluno obj = service.find(id);

        AlunoDTO alunoDTO = new AlunoDTO(
                obj.getMatricula(),
                obj.getNome(),
                obj.getIdade(),
                obj.getEmail(),
                obj.getCursos());
        return ResponseEntity.ok().body(alunoDTO);
    }

    @PostMapping
    @Operation(summary = "Criar aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aluno criado com sucesso", content = @Content(schema = @Schema(implementation = AlunoDTO.class))),
            @ApiResponse(responseCode = "400", description = "ERRO - Requisição inválida. Verifique os campos obrigatórios", content = @Content),
            @ApiResponse(responseCode = "409", description = "ERRO - Conflito. Email já foi utilizado", content = @Content)})
    public ResponseEntity<?> save(@RequestBody @Valid AlunoDTO alunoDTO) {

        if (service.existsByEmail(alunoDTO.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Conflito: Email já foi utilizado!");
        }

        Aluno obj = service.save(alunoDTO);

        AlunoDTO objAlunoDTO = new AlunoDTO(
                obj.getMatricula(),
                obj.getNome(),
                obj.getIdade(),
                obj.getEmail(),
                obj.getCursos());

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(obj.getMatricula())
                .toUri();

        return ResponseEntity.created(uri).body(objAlunoDTO);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualizar aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "ERRO - Requisição inválida. Verifique os campos obrigatórios", content = @Content),
            @ApiResponse(responseCode = "404", description = "ERRO - Aluno não encontrado. A resposta de erro incluirá informações sobre o status, a mensagem e o timestamp", content = @Content)})
    public ResponseEntity<AlunoDTO> update(@PathVariable(value = "id") Long id,
                                        @RequestBody @Valid AlunoDTO alunoDTO) {
        Aluno obj = service.update(id, alunoDTO);

        AlunoDTO objAlunoDTO = new AlunoDTO(
                obj.getMatricula(),
                obj.getNome(),
                obj.getIdade(),
                obj.getEmail(),
                obj.getCursos());
        return ResponseEntity.ok().body(objAlunoDTO);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Excluir aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Aluno excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "ERRO - Aluno não encontrado. A resposta de erro incluirá informações sobre o status, a mensagem e o timestamp", content = @Content) })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
