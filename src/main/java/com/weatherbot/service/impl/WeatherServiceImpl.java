package com.weatherbot.service.impl;

import com.google.gson.Gson;
import com.weatherbot.exceptions.IncorrectCityNameException;
import com.weatherbot.model.Weather;
import com.weatherbot.service.WeatherService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class WeatherServiceImpl implements WeatherService {
    @SneakyThrows
    public Weather[] getByCityName(String city) {
        try {
            validateCityName(city);
            var gson = new Gson();

            var httpClient = HttpClient.newBuilder()
                    .build();

            String apiUrl = "https://api.weatherbit.io/v2.0/forecast/daily?city=";
            String apiKey = "&key=d3a84b62dbda4bf1a4eb904e02edfa36";
            String daysUrl = "&days=";
            int daysForecast = 3;
            var request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(apiUrl + city + apiKey + daysUrl + daysForecast))
                    .build();

            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            WeatherResponse weathers = gson.fromJson(response.body(), WeatherResponse.class);

            return weathers.data;
        } catch (IncorrectCityNameException e) {
            System.err.println(e.getMessage());
            return new Weather[]{new Weather()};
        }
    }

    static class WeatherResponse {
        Weather[] data;
    }
}
