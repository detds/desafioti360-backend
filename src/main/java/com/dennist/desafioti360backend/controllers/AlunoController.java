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
import org.modelmapper.ModelMapper;
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
    private AlunoService alunoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @Operation(summary = "Obter todos os alunos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")})
    public ResponseEntity<List<AlunoDTO>> listarTodosOsAlunos() {
        List<Aluno> alunoList = alunoService.listarTodos();

        List<AlunoDTO> alunoDTOList = alunoList.stream()
                .map(aluno -> modelMapper.map(aluno, AlunoDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(alunoDTOList);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Obter aluno pelo número de matrícula")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida"),
            @ApiResponse(responseCode = "404", description = "ERRO - Aluno não encontrado. A resposta de erro incluirá informações sobre o status, a mensagem e o timestamp", content = @Content),
            @ApiResponse(responseCode = "500", description = "ERRO - Erro inesperado", content = @Content)})
    public ResponseEntity<AlunoDTO> buscarUmAluno(@PathVariable(value = "id") Long id) {
        Aluno obj = alunoService.buscarPorId(id);

        AlunoDTO alunoDTO = modelMapper.map(obj, AlunoDTO.class);

        return ResponseEntity.ok().body(alunoDTO);
    }

    @PostMapping
    @Operation(summary = "Criar aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aluno criado com sucesso", content = @Content(schema = @Schema(implementation = AlunoDTO.class))),
            @ApiResponse(responseCode = "400", description = "ERRO - Requisição inválida. Verifique os campos obrigatórios", content = @Content),
            @ApiResponse(responseCode = "409", description = "ERRO - Conflito. Email já foi utilizado", content = @Content)})
    public ResponseEntity<?> cadastrarAluno(@RequestBody @Valid AlunoDTO alunoDTO) {

        if (alunoService.emailJaCadastrado(alunoDTO.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Conflito: Email já foi utilizado!");
        }

        Aluno obj = alunoService.salvar(alunoDTO);

        AlunoDTO objAlunoDTO = modelMapper.map(obj, AlunoDTO.class);

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
    public ResponseEntity<AlunoDTO> atualizarAluno(@PathVariable(value = "id") Long id,
                                                   @RequestBody @Valid AlunoDTO alunoDTO) {
        Aluno obj = alunoService.atualizar(id, alunoDTO);

        AlunoDTO objAlunoDTO = modelMapper.map(obj, AlunoDTO.class);

        return ResponseEntity.ok().body(objAlunoDTO);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Excluir aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Aluno excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "ERRO - Aluno não encontrado. A resposta de erro incluirá informações sobre o status, a mensagem e o timestamp", content = @Content),
            @ApiResponse(responseCode = "409", description = "ERRO - O aluno possui matrícula em um ou mais cursos e não pode ser excluído. A resposta de erro incluirá informações sobre o status, a mensagem e o timestamp", content = @Content) })
    public ResponseEntity<Void> excluirAluno(@PathVariable Long id) {
        alunoService.detelarPorId(id);
        return ResponseEntity.noContent().build();
    }

}
