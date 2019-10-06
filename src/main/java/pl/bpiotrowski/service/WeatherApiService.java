package pl.bpiotrowski.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import pl.bpiotrowski.dto.WeatherData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class WeatherApiService {
    private Gson gson;
    private ResourceLoader resourceLoader;

    @Value("${api.key}")
    private String apiKey;

    public WeatherApiService(Gson gson, ResourceLoader resourceLoader) {
        this.gson = gson;
        this.resourceLoader = resourceLoader;
    }

    public WeatherData fetchWeather() {
        try {
            Resource resource = resourceLoader.getResource("https://api.openweathermap.org/data/2.5/weather?q=Gdansk&APPID=" + apiKey + "&units=metric");
            InputStream is = resource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String json = reader.readLine();
            return gson.fromJson(json, WeatherData.class);
        } catch (IOException e) {
            throw new RuntimeException("Page does not exist");
        }
    }
}
