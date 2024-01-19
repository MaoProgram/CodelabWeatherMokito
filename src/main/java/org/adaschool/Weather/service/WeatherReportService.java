package org.adaschool.Weather.service;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherReportService {

    private static final String API_KEY = "694a9a6652307f4163dfe8587fe5722a";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";



    public WeatherReport getWeatherReport(double latitude, double longitude) {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + "?lat=" + latitude + "&lon=" + longitude + "&appid=" + API_KEY;

        try {
            WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);

            WeatherReport report = new WeatherReport();
            WeatherApiResponse.Main main = response.getMain();
            report.setTemperature(main.getTemperature());
            report.setHumidity(main.getHumidity());

            return report;
        } catch (Exception e) {
            // Manejar errores, por ejemplo, si las coordenadas no son válidas
            return null;
        }
    }
}