package com.example._exercicio2.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/clima")
    public ResponseEntity<String> obterClima(@RequestParam String cidade) {
        String resposta = weatherService.consultarClima(cidade);
        return ResponseEntity.ok(resposta);
    }
}

