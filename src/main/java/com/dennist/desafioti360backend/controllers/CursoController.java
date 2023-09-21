package com.dennist.desafioti360backend.controllers;

import com.dennist.desafioti360backend.dtos.CursoDTO;
import com.dennist.desafioti360backend.models.Curso;
import com.dennist.desafioti360backend.responses.AdicaoRemocaoAlunosResponse;
import com.dennist.desafioti360backend.services.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cursos")
@Tag(name = "Curso", description = "API curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @Operation(summary = "Obter todos os cursos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")})
    public ResponseEntity<List<CursoDTO>> listarTodosOsCursos() {
        List<Curso> cursoList = cursoService.listarTodos();

        List<CursoDTO> cursoDTOList = cursoList.stream()
                .map(curso -> modelMapper.map(curso, CursoDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(cursoDTOList);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Obter curso pelo código")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida"),
            @ApiResponse(responseCode = "404", description = "ERRO - Curso não encontrado. A resposta de erro incluirá informações sobre o status, a mensagem e o timestamp", content = @Content),
            @ApiResponse(responseCode = "500", description = "ERRO - Erro inesperado", content = @Content)})
    public ResponseEntity<Curso> bucarUmCurso(@PathVariable(value = "id") Long id) {
        Curso obj = cursoService.buscarPorId(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @Operation(summary = "Criar curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Curso criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "ERRO - Requisição inválida. Verifique os campos obrigatórios", content = @Content)})
    public ResponseEntity<Curso> cadastrarCurso(@RequestBody @Valid CursoDTO cursoDTO) {

        Curso obj = cursoService.salvar(cursoDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(obj.getCodigo())
                .toUri();

        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualizar curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "ERRO - Requisição inválida. Verifique os campos obrigatórios", content = @Content),
            @ApiResponse(responseCode = "404", description = "ERRO - Curso não encontrado. A resposta de erro incluirá informações sobre o status, a mensagem e o timestamp", content = @Content) })
    public ResponseEntity<Curso> atualizarCurso(@PathVariable(value = "id") Long id,
                                                @RequestBody @Valid CursoDTO cursoDTO) {
        Curso obj = cursoService.atualizar(id, cursoDTO);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Excluir curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Curso excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "ERRO - Curso não encontrado. A resposta de erro incluirá informações sobre o status, a mensagem e o timestamp", content = @Content),
            @ApiResponse(responseCode = "409", description = "ERRO - O curso possui alunos matriculados e não pode ser excluído. A resposta de erro incluirá informações sobre o status, a mensagem e o timestamp", content = @Content) })
    public ResponseEntity<Void> excluirCurso(@PathVariable Long id) {
        cursoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{cursoId}/alunos")
    @Operation(summary = "Adicionar lista de alunos ao curso",
            description = "Adiciona lista de alunos ao curso utilizando os números de matrículas fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alunos adicionados ao curso com sucesso"),
            @ApiResponse(responseCode = "404", description = "ERRO - Objeto (curso ou matrícula) não encontrado. A resposta de erro incluirá informações sobre o status, a mensagem e o timestamp", content = @Content) })
    public ResponseEntity<AdicaoRemocaoAlunosResponse> adicionarListaDeAlunosAoCurso(
            @PathVariable Long cursoId,
            @RequestBody @Schema(example = "{\"matrículas\": [0]}") Map<String, Set<Long>> request) {

        AdicaoRemocaoAlunosResponse response = cursoService.adicionarAlunos(cursoId, request.get("matrículas"));

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/{cursoId}/alunos")
    @Operation(summary = "Remover lista de alunos de um curso",
            description = "Remover lista de alunos de um curso utilizando os números de matrículas fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alunos removidos do curso com sucesso."),
            @ApiResponse(responseCode = "404", description = "ERRO - Objeto (curso ou matrícula) não encontrado. A resposta de erro incluirá informações sobre o status, a mensagem e o timestamp", content = @Content) })
    public ResponseEntity<AdicaoRemocaoAlunosResponse> removerListaDeAlunosDoCurso(
            @PathVariable Long cursoId,
            @RequestBody @Schema(example = "{\"matrículas\": [0]}") Map<String, Set<Long>> request) {

        AdicaoRemocaoAlunosResponse response = cursoService.removerAlunos(cursoId, request.get("matrículas"));

        return ResponseEntity.ok().body(response);
    }
}
