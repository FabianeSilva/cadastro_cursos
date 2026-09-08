package com.fabianeSilva.cadastro_cursos.infrastructure.repositories;

import com.fabianeSilva.cadastro_cursos.infrastructure.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CursoRepository extends JpaRepository<Curso, UUID> {

    public List<Curso> findByNomeCursoContainingIgnoreCaseOrCategoriaContainingIgnoreCase
            (String nomeCurso, String categoria);

}
