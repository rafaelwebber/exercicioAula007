package com.example._exercicio2.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class WeatherService {

    @Value("${openweather.apikey}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String consultarClima(String cidade) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cidade +
                "&appid=" + apiKey + "&units=metric&lang=pt_br";

        Map<String, Object> resposta = restTemplate.getForObject(url, Map.class);

        Map<String, Object> main = (Map<String, Object>) resposta.get("main");
        List<Map<String, Object>> weatherList = (List<Map<String, Object>>) resposta.get("weather");
        Map<String, Object> weather = weatherList.get(0);

        String descricao = weather.get("description").toString();
        double temperatura = Double.parseDouble(main.get("temp").toString());
        int umidade = (int) Double.parseDouble(main.get("humidity").toString());


        return String.format("🌦️ Em %s está %s com %.1f°C e umidade de %d%%. Leve um guarda-chuva só por precaução!",
                cidade, descricao, temperatura, umidade);
    }
}
