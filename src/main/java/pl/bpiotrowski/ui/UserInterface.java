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
        String city = "gdansk";
        Scanner in = new Scanner(System.in);
        while (!city.equals("n")) {
            weatherData = weatherApiService.fetchWeather(city);
            System.out.println("Weather in " + weatherData.getName() + ": " + weatherData.getWeather()[0].getDescription());
            city = in.nextLine();
        }
    }
}
