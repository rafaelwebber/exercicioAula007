package org.example;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class FipeService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "https://parallelum.com.br/fipe/api/v1/carros";

    public FipeResponse consultarValor(CarroRequest carro) {

        List<Map<String, Object>> marcas = restTemplate.getForObject(baseUrl + "/marcas", List.class);
        String marcaId = findId(marcas, carro.getMarca());

        Map<String, Object> modelosResponse = restTemplate.getForObject(baseUrl + "/marcas/" + marcaId + "/modelos", Map.class);
        List<Map<String, Object>> modelos = (List<Map<String, Object>>) modelosResponse.get("modelos");
        String modeloId = findId(modelos, carro.getModelo());

        List<Map<String, Object>> anos = restTemplate.getForObject(baseUrl + "/marcas/" + marcaId + "/modelos/" + modeloId + "/anos", List.class);
        String anoId = findId(anos, String.valueOf(carro.getAno()));

        Map<String, Object> valorMap = restTemplate.getForObject(baseUrl + "/marcas/" + marcaId + "/modelos/" + modeloId + "/anos/" + anoId, Map.class);

        double valor = Double.parseDouble(((String) valorMap.get("Valor")).replace("R$ ", "").replace(".", "").replace(",", "."));
        String mes = (String) valorMap.get("MesReferencia");

        return new FipeResponse(valor, mes);
    }

    private String findId(List<Map<String, Object>> lista, String nome) {
        for (Map<String, Object> item : lista) {
            if (item.get("nome").toString().toLowerCase().contains(nome.toLowerCase())) {
                return item.get("codigo").toString();
            }
        }
        throw new RuntimeException("Não encontrado: " + nome);
    }
}
