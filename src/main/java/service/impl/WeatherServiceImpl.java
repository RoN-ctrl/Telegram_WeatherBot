package service.impl;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import model.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import service.WeatherService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.key}")
    private String apiKey;

    @SneakyThrows
    public Weather getByCityName(String city) {
        validateCityName(city);
        var gson = new Gson();

        var httpClient = HttpClient.newBuilder()
                .build();

        var request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(apiUrl + city + apiKey))
                .build();

        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        WeatherResponse weathers = gson.fromJson(response.body(), WeatherResponse.class);

        int ARRAY_INDEX = 0;
        return weathers.data[ARRAY_INDEX];
    }

    static class WeatherResponse {
        Weather[] data;
    }
}
