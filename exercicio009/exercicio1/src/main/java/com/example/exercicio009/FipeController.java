package com.example.exercicio009;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FipeController {

    @Autowired
    private FipeService fipeService;

    @PostMapping("/fipe")
    public ResponseEntity<String> consultarFipe(@RequestBody CarroRequest carro) {
        String valor = fipeService.consultarValorFipe(carro.getMarca(), carro.getModelo(), carro.getAno());
        return ResponseEntity.ok("Valor FIPE: " + valor);
    }
}
