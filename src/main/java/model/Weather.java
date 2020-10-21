package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
public class Weather {

    private String city_name;
    private String country_code;
    private String wind_cdir_full;
    private double temp;

    @Override
    public String toString() {
        char degree = 176;
        return city_name + ", " + country_code + ":\n" +
                temp + degree +
                "\nwind " + wind_cdir_full;
    }
}
