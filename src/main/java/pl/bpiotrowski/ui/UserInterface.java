package pl.bpiotrowski.ui;

import org.springframework.stereotype.Component;
import pl.bpiotrowski.dto.WeatherData;
import pl.bpiotrowski.service.WeatherApiService;

@Component
public class UserInterface {
    public WeatherApiService weatherApiService;

    public UserInterface(WeatherApiService weatherApiService) {
        this.weatherApiService = weatherApiService;
    }

    public void start() {
        WeatherData weatherData = weatherApiService.fetchWeather();
        System.out.println("Weather in " + weatherData.getName() + ": " + weatherData.getWeather()[0].getDescription());
    }
}
