package com.dennist.desafioti360backend.services;

import com.dennist.desafioti360backend.dtos.CursoDTO;
import com.dennist.desafioti360backend.models.Aluno;
import com.dennist.desafioti360backend.models.Curso;
import com.dennist.desafioti360backend.repositories.CursoRepository;
import com.dennist.desafioti360backend.responses.AdicaoRemocaoAlunosResponse;
import com.dennist.desafioti360backend.services.exceptions.DataIntegrityException;
import com.dennist.desafioti360backend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AlunoService alunoService;

    public Page<Curso> listarTodos(Pageable pageable) {
        return cursoRepository.findAll(pageable);
    }

    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public Curso salvar(CursoDTO cursoDTO) {

        Curso obj = new Curso();
        BeanUtils.copyProperties(cursoDTO, obj);

        return cursoRepository.save(obj);
    }

    public void deletarPorId(Long id) {
        Curso cursoEncontrado = buscarPorId(id);

        if (cursoEncontrado.getAlunos().isEmpty()) {
            cursoRepository.deleteById(id);
        } else {
            throw new DataIntegrityException("O curso possui alunos matriculados e não pode ser excluído.");
        }
    }

    @Transactional
    public Curso atualizar(Long id, CursoDTO cursoDTO) {
        Curso entity = buscarPorId(id);
        BeanUtils.copyProperties(cursoDTO, entity);
        return cursoRepository.save(entity);
    }

    @Transactional
    public AdicaoRemocaoAlunosResponse adicionarAlunos(Long cursoId, Set<Long> matriculas) {
        Curso cursoEntity = buscarPorId(cursoId);
        List<Aluno> alunosEncontrados = alunoService.buscarTodosPorId(matriculas);

        verificarAlunosEncontrados(alunosEncontrados, matriculas);

        cursoEntity.getAlunos().addAll(alunosEncontrados);
        cursoRepository.save(cursoEntity);

        return new AdicaoRemocaoAlunosResponse(
                "Matrículas adicionadas ao curso com sucesso!",
                cursoEntity.getNome(),
                matriculas);
    }

    @Transactional
    public AdicaoRemocaoAlunosResponse removerAlunos(Long cursoId, Set<Long> matriculas) {
        Curso cursoEntity = buscarPorId(cursoId);

        List<Aluno> alunosEncontradosNoCurso = cursoEntity.getAlunos().stream()
                .filter(aluno -> matriculas.contains(aluno.getMatricula()))
                .toList();

        verificarAlunosEncontrados(alunosEncontradosNoCurso, matriculas);

        cursoEntity.getAlunos().removeAll(alunosEncontradosNoCurso);
        cursoRepository.save(cursoEntity);

        return new AdicaoRemocaoAlunosResponse(
                "Matrículas removidas do curso com sucesso.",
                cursoEntity.getNome(),
                matriculas);
    }

    private void verificarAlunosEncontrados(List<Aluno> alunosEncontrados,
                                            Set<Long> matriculas) {
        if (alunosEncontrados.size() < matriculas.size()) {
            Set<Long> matriculasEncontradas = alunosEncontrados.stream()
                    .map(Aluno::getMatricula)
                    .collect(Collectors.toSet());

            List<Long> matriculasNaoEncontradas = matriculas.stream()
                    .filter(matricula -> !matriculasEncontradas.contains(matricula))
                    .toList();

            throw new ObjectNotFoundException("Matrícula(s) não encontrada(s)! Id(s): " + matriculasNaoEncontradas);
        }
    }
}
