package de.weather.application;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @CrossOrigin(origins = "http://localhost:3000") // Adjust the origin as needed
    @GetMapping("/getTemperature")
    public DataResponse getTemperature() {
      return new DataResponse("20"); // Example data, replace with actual logic to get temperature
    }

 
 

    static class DataResponse {
    private String temperature;

    public DataResponse(String temperature) {
        this.temperature = temperature;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}

}
