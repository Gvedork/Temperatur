package de.weather.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// import de.weather.application.Weather.WeatherFeelingRequest;

import java.util.List;

@RestController
public class WeatherController {
    
    @Autowired
    private WeatherService weatherService;

    // @GetMapping("/weather")
    // public List<Weather> getAllWeather() {
    //     return weatherService.getAllWeather();
    // }

    @GetMapping("/weather")
    public List<Weather> getAllWeather() {
        return weatherService.getAllWeatherWithFeeling();
    }

    @PutMapping("/weatherFeeling")
    public void putWeatherFeeling(@RequestBody Weather.WeatherFeelingRequest request) {
        weatherService.saveWeatherFeeling(request.getDate(), request.getFeeling());
    }
}
