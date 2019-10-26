package pl.bpiotrowski.ui;

import org.springframework.stereotype.Component;
import pl.bpiotrowski.dto.WeatherData;
import pl.bpiotrowski.service.WeatherApiService;

import java.util.Scanner;

@Component
public class UserInterface {
    public WeatherApiService weatherApiService;

    public UserInterface(WeatherApiService weatherApiService) {
        this.weatherApiService = weatherApiService;
    }

    public void start() {
        WeatherData weatherData;
        String var = "y";
        Scanner in = new Scanner(System.in);
        while (var.equals("y")) {
            weatherData = weatherApiService.fetchWeather();
            System.out.println("Weather in " + weatherData.getName() + ": " + weatherData.getWeather()[0].getDescription());
            var = in.nextLine();
        }
    }
}
