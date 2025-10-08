package com.example.exemplo_repositorio.controllers;


import com.example.exemplo_repositorio.models.Jogador;
import com.example.exemplo_repositorio.services.JogadorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/jogador")
public class JogadorController {

    private JogadorService jogadorService;

    public JogadorController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @PostMapping
    public Jogador saveJogador(@RequestBody Jogador jogador) {
        return jogadorService.saveJogador(
                jogador.getNome(),
                jogador.getSobrenome(),
                jogador.getPosicao()
        );
    }

    @GetMapping
    public List<Jogador> getAllJogador(@RequestParam(required = false) String nome) {
        if (nome != null) {
            return jogadorService.findByNome(nome);
        }
        return jogadorService.getAlljogador();
    }

    @GetMapping("/{id}")
    public Jogador getJogadorById(@PathVariable UUID id) {
        return jogadorService.getJogadorById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteJogador(@PathVariable UUID id) {
        jogadorService.deleteJogador(id);
    }
}
