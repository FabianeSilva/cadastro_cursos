package com.fabianeSilva.cadastro_cursos.controller;

import com.fabianeSilva.cadastro_cursos.business.CursoService;
import com.fabianeSilva.cadastro_cursos.infrastructure.entities.Curso;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> salvarCurso(@RequestBody Curso curso){
        service.salvarCurso(curso);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Curso>> buscarCursos(){
        return ResponseEntity.ok(service.buscarCursos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarCursoPorId(@PathVariable (name = "id") UUID id){
        return ResponseEntity.ok(service.buscarCursoPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCursoPorId(@PathVariable (name = "id") UUID id){
        service.deletarCursoPorId(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizarCursoPorId(@RequestParam (name = "id") UUID id,
                                                    @RequestBody Curso curso){
        service.atualizarCursoPorId(id, curso);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<Void> alterarStatusCurso(@PathVariable (name = "id") UUID id){
        service.alterarStatusCurso(id);
        return ResponseEntity.noContent().build();
    }

}
