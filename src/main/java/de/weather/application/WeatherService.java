package de.weather.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    public List<Weather> getWeatherByDate(String startDate, String endDate) {
//        String csvFile = "/home/praktikant/Temperatur/src/main/resources/weather.csv"; 
        List<Weather> result = new ArrayList<>();
        String line;
        String cvsSplitBy = ",";
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new ClassPathResource("weather.csv").getInputStream()))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                LocalDate current = LocalDate.parse(data[0]);
                if (current.compareTo(start) >= 0 && current.compareTo(end) <= 0) {
                    result.add(new Weather(
                        data[0],
                        Double.parseDouble(data[1]),
                        Double.parseDouble(data[2]),
                        Double.parseDouble(data[3]),
                        Double.parseDouble(data[4]),
                        data[5]
                    ));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
