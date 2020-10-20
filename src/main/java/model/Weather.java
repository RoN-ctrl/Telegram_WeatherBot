package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Weather {

    private Object weather;
    private String city_name;
    private String country_code;
    private String wind_cdir_full;
    private String clouds; //percent
    private double temp;
}
