package pl.bpiotrowski.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import pl.bpiotrowski.dto.WeatherData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@RequiredArgsConstructor
@Component
public class WeatherApiService {
    private final Gson gson;
    private final ResourceLoader resourceLoader;
    private final WeatherDataCache cache;

    @Value("${api.key}")
    private String apiKey;

    @ExecutionTimeLog
    public WeatherData fetchWeather(String city) {
        WeatherData cachedData = cache.find(city);
        if(cachedData != null) {
            System.out.println("Data from cache");
            return cachedData;
        }

        try {
            System.out.println("Data from api");
            Resource resource = resourceLoader.getResource("https://api.openweathermap.org/data/2.5/weather?q=Gdansk&APPID=" + apiKey + "&units=metric");
            InputStream is = resource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String json = reader.readLine();
            WeatherData data = gson.fromJson(json, WeatherData.class);
            data.setTimestamp(System.currentTimeMillis());
            cache.save(city, data);
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Page does not exist");
        }
    }
}
