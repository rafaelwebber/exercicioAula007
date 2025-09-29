package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fipe")
public class FipeController {

    @Autowired
    private FipeService fipeService;

    @PostMapping
    public FipeResponse consultar(@RequestBody CarroRequest carro) {
        return fipeService.consultarValor(carro);
    }
}
