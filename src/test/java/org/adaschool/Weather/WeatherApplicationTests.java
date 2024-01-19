package org.adaschool.Weather;

import org.adaschool.Weather.controller.WeatherReportController;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class WeatherApplicationTests {

	@Mock
	private WeatherReportService weatherReportService;

	@InjectMocks
	private WeatherReportController weatherReportController;
	@Test
	void getWeatherReportTest() {

		double latitude = 5.0;
		double longitude = 10.0;
		WeatherReport expectedReport = new WeatherReport();
		when(weatherReportService.getWeatherReport(latitude, longitude)).thenReturn(expectedReport);
		WeatherReport actualReport = weatherReportController.getWeatherReport(latitude, longitude);
		verify(weatherReportService, times(1)).getWeatherReport(latitude, longitude);
		assertEquals(expectedReport, actualReport);
	}

}
