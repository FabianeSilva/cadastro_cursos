package com.fabianeSilva.cadastro_cursos.business;

import com.fabianeSilva.cadastro_cursos.infrastructure.entities.Curso;
import com.fabianeSilva.cadastro_cursos.infrastructure.repositories.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CursoService {

    private final CursoRepository repository;

    public CursoService(CursoRepository repository){
        this.repository=repository;
    }

    public void salvarCurso(Curso curso){
        repository.saveAndFlush(curso);
    }

    public List<Curso> buscarCursos(){
        return repository.findAll();
    }

    public Curso buscarCursoPorId(UUID id){
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Id não encontrado!"));
    }

    public List<Curso> buscarCursoPorNomeOuCategoria(String nomeCurso, String categoria){

        if (nomeCurso != null && nomeCurso.isBlank()){
            nomeCurso = null;
        }
        if (categoria != null && categoria.isBlank()){
            categoria = null;
        }

        return repository.findByNomeCursoContainingIgnoreCaseOrCategoriaContainingIgnoreCase(nomeCurso, categoria);

    }

    public void deletarCursoPorId(UUID id){
        repository.deleteById(id);
    }

    public void atualizarCursoPorId(UUID id, Curso curso){
        Curso cursoEntity = repository.findById(id).orElseThrow(()-> new RuntimeException("Id não encontrado!"));

        Curso cursoAtualizado= Curso.builder()
                .nomeCurso(curso.getNomeCurso() != null ? curso.getNomeCurso() : cursoEntity.getNomeCurso())
                .categoria(curso.getCategoria() != null ? curso.getCategoria() : cursoEntity.getCategoria())
                .ativo(curso.getAtivo() != null ? curso.getAtivo() : cursoEntity.getAtivo())
                .id(cursoEntity.getId())
                .build();

            repository.saveAndFlush(cursoAtualizado);
    }
}
