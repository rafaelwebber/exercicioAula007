package org.example.controllers;

import org.example.models.Autor;
import org.example.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {
    @Autowired
    private AutorRepository autorRepo;

    @GetMapping
    public List<Autor> listar(){
        return autorRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscar(@PathVariable Long id){
        return autorRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Autor criar(@RequestBody Autor autor){
        return autorRepo.save(autor);
    }

    @PatchMapping("/id")
    public ResponseEntity<Autor> atualizar(@PathVariable Long id, @RequestBody Autor dados){
        return autorRepo.findById(id).map(autor -> {
            autor.setNome(dados.getNome());
            autor.setNacionalidade(dados.getNacionalidade());
            return ResponseEntity.ok(autorRepo.save(autor));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        autorRepo.deleteById(id);
    }
}
