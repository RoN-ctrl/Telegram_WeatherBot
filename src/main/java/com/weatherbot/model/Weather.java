package com.weatherbot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

    private String city_name;
    //    private String country_code;
    private String valid_date;      //Date the forecast is valid for in format YYYY-MM-DD
    private String wind_cdir_full;  //Verbal wind direction
    private double wind_spd;        //Wind speed (m/s)
    private double max_temp;        //Maximum Temperature (Celsius)
    private double min_temp;        //Minimum Temperature (Celsius)
    private double precip;          //Accumulated liquid equivalent precipitation (mm)
    private int snow;               //Accumulated snowfall (mm)
    private int pop;                //Probability of Precipitation (%)

    @Override
    public String toString() {
        char degree = 176;
        return valid_date +
                "\ntemp: " + min_temp + degree + "..." + max_temp + degree +
                "\nwind: " + wind_cdir_full + " " + wind_spd + "m/s" +
                "\nprecipitation: " + pop + "%" +
                "\nsnow: " + snow + "mm" +
                "\nrain: " + precip + "mm\n\n";
    }
}
