package com.example.exercicioAula007;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Recomendador {

    @GetMapping("/recomendar")
    public String recomendarDestino(@RequestParam String clima, @RequestParam String estilo) {
        if (clima.equalsIgnoreCase("calor") && estilo.equalsIgnoreCase("natureza")) {
            return "Rio de Janeiro";
        } else if (clima.equalsIgnoreCase("frio") && estilo.equalsIgnoreCase("aventura")) {
            return "Gramado";
        } else if (clima.equalsIgnoreCase("calor") && estilo.equalsIgnoreCase("cultura")) {
            return "Salvador";
        } else if (clima.equalsIgnoreCase("frio") && estilo.equalsIgnoreCase("romance")) {
            return "Campos do Jordão";
        } else {
            return "Destino não encontrado para essa combinação.";
        }
    }
}
