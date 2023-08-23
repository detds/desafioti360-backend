package com.dennist.desafioti360backend.controllers;

import com.dennist.desafioti360backend.dtos.MatriculaDTO;
import com.dennist.desafioti360backend.models.Matricula;
import com.dennist.desafioti360backend.services.MatriculaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/matriculas")
@Tag(name = "Matrícula", description = "API matrícula")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @PostMapping("/aluno/{alunoId}/curso/{cursoId}")
    @Operation(summary = "Matricular um aluno em um curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aluno matriculado no curso com sucesso"),
            @ApiResponse(responseCode = "409", description = "ERRO - Conflito. Aluno já possui matrícula neste curso", content = @Content)})
    public ResponseEntity<MatriculaDTO> adicionarAlunoACurso(@PathVariable Long alunoId,
                                                             @PathVariable Long cursoId) {

        Matricula obj = matriculaService.adicionarAlunoACurso(alunoId, cursoId);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/aluno/{alunoId}/curso/{cursoId")
                .buildAndExpand(obj.getAluno().getMatricula(), obj.getCurso().getCodigo())
                .toUri();

        MatriculaDTO objDTO = new MatriculaDTO(obj.getId(), obj.getDataMatriculaCurso());

        return ResponseEntity.created(uri).body(objDTO);
    }

    @DeleteMapping("/aluno/{alunoId}/curso/{cursoId}")
    @Operation(summary = "Remover a matrícula de um aluno de um curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Aluno removido do curso com sucesso"),
            @ApiResponse(responseCode = "404", description = "ERRO - Matrícula não encontrada. A resposta de erro incluirá informações sobre o status, a mensagem e o timestamp", content = @Content) })
    public ResponseEntity<Void> removerAlunoDoCurso(@PathVariable Long alunoId,
                                                 @PathVariable Long cursoId) {

        matriculaService.removerAlunoDoCurso(alunoId, cursoId);

        return ResponseEntity.noContent().build();
    }
}
