package service;

import exceptions.IncorrectCityNameException;
import model.Weather;

public interface WeatherService {
    String CITY_REGEX = "^\\p{Lu}\\p{Ll}+( \\p{Lu}\\p{Ll}+)*$";

    Weather getByCityName(String city);

    default void validateCityName(String city) {
        if (!city.matches(CITY_REGEX)) {
            throw new IncorrectCityNameException(String.format("Incorrect city name %s", city));
        }
    }
}
