package org.adaschool.Weather.service;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class WeatherReportServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherReportService weatherReportService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getWeatherReport_Success() {
        double latitude = 1.0;
        double longitude = 2.0;

        // Mocking the response from the external API
        WeatherApiResponse mockApiResponse = new WeatherApiResponse();
        WeatherApiResponse.Main main = new WeatherApiResponse.Main();
        main.setTemperature(25.0);
        main.setHumidity(80);
        mockApiResponse.setMain(main);

        // Configure the RestTemplate mock to return the mockApiResponse
        when(restTemplate.getForObject(any(String.class), any())).thenReturn(mockApiResponse);

        // Perform the actual method invocation
        WeatherReport result = weatherReportService.getWeatherReport(latitude, longitude);

        // Verify that RestTemplate.getForObject is called with the correct URL
        // and the expected response type (WeatherApiResponse.class)
        verify(restTemplate).getForObject(any(String.class), any());

        // Verify that the WeatherReport is created correctly based on the mocked response
        assertEquals(25.0, result.getTemperature());
        assertEquals(80, result.getHumidity());
    }

    @Test
    void getWeatherReport_ExceptionHandling() {
        double latitude = 1.0;
        double longitude = 2.0;

        // Mocking an exception thrown by the external API call
        when(restTemplate.getForObject(any(String.class), any())).thenThrow(new RuntimeException("Simulated API error"));

        // Perform the actual method invocation
        WeatherReport result = weatherReportService.getWeatherReport(latitude, longitude);

        // Verify that RestTemplate.getForObject is called with the correct URL
        // and the expected response type (WeatherApiResponse.class)
        verify(restTemplate).getForObject(any(String.class), any());

        // Verify that the method gracefully handles the exception and returns null
        assertNull(result);
    }
}
