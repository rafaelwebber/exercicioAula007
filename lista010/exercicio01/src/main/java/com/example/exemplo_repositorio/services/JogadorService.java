package com.example.exemplo_repositorio.services;

import com.example.exemplo_repositorio.models.Jogador;
import com.example.exemplo_repositorio.repositories.JogadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JogadorService {

    private JogadorRepository jogadorRepository;

    public JogadorService(JogadorRepository jogadorRepository){
        this.jogadorRepository = jogadorRepository;
    }

    public Jogador saveJogador(String nome, String sobrenome, String posicao){
        Jogador novoJogador = new Jogador();
        novoJogador.setNome(nome);
        novoJogador.setSobrenome(sobrenome);
        novoJogador.setClube("Criciuma");
        novoJogador.setIdade(18);
        novoJogador.setPosicao(posicao);
        return jogadorRepository.save(novoJogador);
    }

    public List<Jogador> getAlljogador(){
        return jogadorRepository.findAll();
    }

    public Jogador getJogadorById(UUID id){
        Optional<Jogador> jogador = jogadorRepository.findById(id);
        return jogador.orElse(null); // ou lançar exceção se preferir
    }

    public void deleteJogador(UUID id){
        jogadorRepository.deleteById(id);
    }

    public List<Jogador> findByNome(String nome){
        return jogadorRepository.findByNomeContainingIgnoreCase(nome);
    }
}
