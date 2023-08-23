package com.dennist.desafioti360backend.controllers;

import com.dennist.desafioti360backend.dtos.CursoDTO;
import com.dennist.desafioti360backend.models.Curso;
import com.dennist.desafioti360backend.services.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cursos")
@Tag(name = "Curso", description = "API curso")
public class CursoController {

    @Autowired
    private CursoService service;

    @GetMapping
    @Operation(summary = "Obter todos os cursos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")})
    public ResponseEntity<List<CursoDTO>> findAll() {
        List<Curso> cursoList = service.findAll();

        List<CursoDTO> cursoDTOList = cursoList.stream()
                .map(curso -> new CursoDTO(curso.getCodigo(), curso.getNome()))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(cursoDTOList);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Obter curso pelo código")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida"),
            @ApiResponse(responseCode = "404", description = "ERRO - Curso não encontrado. A resposta de erro incluirá informações sobre o status, a mensagem e o timestamp", content = @Content),
            @ApiResponse(responseCode = "500", description = "ERRO - Erro inesperado", content = @Content)})
    public ResponseEntity<CursoDTO> find(@PathVariable(value = "id") Long id) {
        Curso obj = service.find(id);
        CursoDTO objDTO = new CursoDTO(obj.getCodigo(), obj.getNome());
        return ResponseEntity.ok().body(objDTO);
    }

    @PostMapping
    @Operation(summary = "Criar curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Curso criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "ERRO - Requisição inválida. Verifique os campos obrigatórios", content = @Content)})
    public ResponseEntity<CursoDTO> save(@RequestBody @Valid CursoDTO cursoDTO) {

        Curso obj = service.save(cursoDTO);

        CursoDTO objDTO = new CursoDTO(obj.getCodigo(), obj.getNome());

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(obj.getCodigo())
                .toUri();

        return ResponseEntity.created(uri).body(objDTO);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualizar curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "ERRO - Requisição inválida. Verifique os campos obrigatórios", content = @Content),
            @ApiResponse(responseCode = "404", description = "ERRO - Curso não encontrado. A resposta de erro incluirá informações sobre o status, a mensagem e o timestamp", content = @Content) })
    public ResponseEntity<CursoDTO> update(@PathVariable(value = "id") Long id,
                                        @RequestBody @Valid CursoDTO cursoDTO) {
        Curso obj = service.update(id, cursoDTO);
        CursoDTO objDTO = new CursoDTO(obj.getCodigo(), obj.getNome());
        return ResponseEntity.ok().body(objDTO);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Excluir curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Curso excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "ERRO - Curso não encontrado. A resposta de erro incluirá informações sobre o status, a mensagem e o timestamp", content = @Content) })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
