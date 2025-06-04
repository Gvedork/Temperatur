package de.weather.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class WeatherController {
    
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/{date}")
    public List<Weather> getWeatherByDate(@PathVariable String date) {
        return weatherService.getWeatherByDate(date);
    }
}
