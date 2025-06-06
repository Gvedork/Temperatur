package de.weather.application;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
public class Weather {

    // @CrossOrigin(origins = "http://localhost:3000") // Adjust the origin as needed
    // @GetMapping("/getTemperature")
    // public DataResponse getTemperature() {
    //   return new DataResponse("20"); // Example data, replace with actual logic to get temperature
    // }

    
    private String date;
    private double temp_min;
    private double temp_max;
    private double percipitation;
    private double wind;
    private String weather_type;

 
    public Weather(String date, double temp_min, double temp_max, double percipitation, double wind, String weather_type) {
        this.date = date;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.percipitation = percipitation;
        this.wind = wind;
        this.weather_type = weather_type;
    }

    public String getDate() {
        return date;
    }

    public String getTempMin() {
        return temp_min + "°C";
    }

    public String getTempMax() {
        return temp_max + "°C";
    }

    public String getPercipitation() {
        return percipitation + "mm";
    }
    public String getWind() {
        return wind + "km/h";
    }
    public String getWeatherType() {
        return weather_type;
    }

    private Double feeling;

    public void setFeeling(Double feeling) {
        this.feeling = feeling;
    }
    public Double getFeeling() {
        return feeling;
    }   

// Add this DTO class (can be a separate file or static inner class)
    public static class WeatherFeelingRequest {
    private String date;
    private double feeling;

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public double getFeeling() { return feeling; }
    public void setFeeling(double feeling) { this.feeling = feeling; }
    }
}

