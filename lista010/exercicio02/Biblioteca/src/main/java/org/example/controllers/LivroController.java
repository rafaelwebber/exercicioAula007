package org.example.controllers;

import org.example.models.Livro;
import org.example.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroRepository livroRepo;

    @GetMapping
    public List<Livro> listar(){
        return livroRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscar(@PathVariable Long id){
        return livroRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Livro criar(@RequestBody Livro livro){
        return livroRepo.save(livro);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @RequestBody Livro dados){
        return livroRepo.findById(id).map(livro -> {
            livro.setTitulo(dados.getTitulo());
            livro.setGenero(dados.getGenero());
            livro.setAutor(dados.getAutor());
            return ResponseEntity.ok(livroRepo.save(livro));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        livroRepo.deleteById(id);
    }
}
