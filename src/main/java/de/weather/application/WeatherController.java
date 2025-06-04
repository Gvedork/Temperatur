package de.weather.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class WeatherController {
    
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/{startDate}/{endDate}")
    public List<Weather> getWeatherByDate(@PathVariable String startDate, @PathVariable String endDate) {
        return weatherService.getWeatherByDate(startDate, endDate);
    }
}
