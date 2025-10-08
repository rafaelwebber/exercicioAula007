package com.example.exercicio009;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

    @Service
    public class FipeService {

        private final RestTemplate restTemplate = new RestTemplate();
        private final String BASE_URL = "https://parallelum.com.br/fipe/api/v1/carros";

        public String consultarValorFipe(String marca, String modelo, String ano) {
            // Buscar código da marca
            List<Map<String, Object>> marcas = restTemplate.getForObject(BASE_URL + "/marcas", List.class);
            String codigoMarca = marcas.stream()
                    .filter(m -> m.get("nome").toString().equalsIgnoreCase(marca))
                    .findFirst()
                    .map(m -> m.get("codigo").toString())
                    .orElseThrow(() -> new RuntimeException("Marca não encontrada"));

            // Buscar código do modelo
            Map<String, Object> modelosResponse = restTemplate.getForObject(BASE_URL + "/marcas/" + codigoMarca + "/modelos", Map.class);
            List<Map<String, Object>> modelos = (List<Map<String, Object>>) modelosResponse.get("modelos");
            String codigoModelo = modelos.stream()
                    .filter(m -> m.get("nome").toString().equalsIgnoreCase(modelo))
                    .findFirst()
                    .map(m -> m.get("codigo").toString())
                    .orElseThrow(() -> new RuntimeException("Modelo não encontrado"));

            // Buscar código do ano
            List<Map<String, Object>> anos = restTemplate.getForObject(BASE_URL + "/marcas/" + codigoMarca + "/modelos/" + codigoModelo + "/anos", List.class);
            String codigoAno = anos.stream()
                    .filter(a -> a.get("nome").toString().contains(ano))
                    .findFirst()
                    .map(a -> a.get("codigo").toString())
                    .orElseThrow(() -> new RuntimeException("Ano não encontrado"));

            // Buscar valor FIPE
            Map<String, Object> resultado = restTemplate.getForObject(BASE_URL + "/marcas/" + codigoMarca + "/modelos/" + codigoModelo + "/anos/" + codigoAno, Map.class);
            return resultado.get("Valor").toString();
        }
    }
