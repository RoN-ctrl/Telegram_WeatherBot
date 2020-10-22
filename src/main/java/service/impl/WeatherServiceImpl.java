package service.impl;

import com.google.gson.Gson;
import exceptions.IncorrectCityNameException;
import lombok.SneakyThrows;
import model.Weather;
import org.springframework.stereotype.Service;
import service.WeatherService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class WeatherServiceImpl implements WeatherService {

    @SneakyThrows
    public Weather getByCityName(String city) {
        try {
            validateCityName(city);
            var gson = new Gson();

            var httpClient = HttpClient.newBuilder()
                    .build();

            String apiUrl = "http://api.weatherbit.io/v2.0/current?city=";
            String apiKey = "&key=d3a84b62dbda4bf1a4eb904e02edfa36";
            var request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(apiUrl + city + apiKey))
                    .build();

            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            WeatherResponse weathers = gson.fromJson(response.body(), WeatherResponse.class);

            int arrayIndex = 0;
            return weathers.data[arrayIndex];
        } catch (IncorrectCityNameException e) {
            System.err.println(e.getMessage());
            return new Weather();
        }
    }

    static class WeatherResponse {
        Weather[] data;
    }
}
