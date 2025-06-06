package de.weather.application;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class WeatherService {

    private List<Weather> weatherList = new ArrayList<>();
    private final String csvFile = "/home/praktikant/RaspberryPythonToCSV/res/output.csv"; // Adjust path as needed

    public WeatherService() {
        loadWeatherData();
    }

    @Scheduled(fixedRate = 10000) // every 60,000 ms = 1 minute
    public void loadWeatherData() {
        List<Weather> tempList = new ArrayList<>();
        String line;
        String cvsSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                if (data[0].equalsIgnoreCase("date") || data.length < 6) continue; // skip header
                tempList.add(new Weather(
                    data[0],
                    Double.parseDouble(data[1]),
                    Double.parseDouble(data[2]),
                    Double.parseDouble(data[3]),
                    Double.parseDouble(data[4]),
                    data[5]
                ));
            }
            weatherList = tempList;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Weather> getAllWeather() {
    return weatherList;
}

    public List<Weather> getWeatherByDate(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<Weather> result = new ArrayList<>();
        for (Weather w : weatherList) {
            LocalDate current = LocalDate.parse(w.getDate());
            if (current.compareTo(start) >= 0 && current.compareTo(end) <= 0) {
                result.add(w);
            }
        }
        return result;
    }

    private final Map<String, Double> weatherFeelings = new ConcurrentHashMap<>();

    public void saveWeatherFeeling(String date, double feeling) {
        weatherFeelings.put(date, feeling);
        System.out.println("Received weather feeling: " + feeling + " for date: " + date);
    }

    public Double getWeatherFeeling(String date) {
        return weatherFeelings.get(date);
    }

    public List<Weather> getAllWeatherWithFeeling() {
    List<Weather> result = new ArrayList<>();
    for (Weather weather : weatherList) {
        Double feeling = weatherFeelings.get(weather.getDate());
        if (feeling != null) {
            weather.setFeeling(feeling); // You need a setFeeling(double) method in Weather
        }
        result.add(weather);
    }
    return result;
}
}