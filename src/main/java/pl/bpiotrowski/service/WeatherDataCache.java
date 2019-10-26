package pl.bpiotrowski.service;

import org.springframework.stereotype.Component;
import pl.bpiotrowski.dto.WeatherData;

import java.util.HashMap;
import java.util.Map;

@Component
public class WeatherDataCache {

    private static final long TEN_SECONDS = 10000;
    private final Map<String, WeatherData> weatherDataCache = new HashMap<>();

    public WeatherData find(String city) {
        WeatherData cachedCityData = weatherDataCache.get(city.toLowerCase());
        long now = System.currentTimeMillis();

        if(cachedCityData == null || cachedCityData.getTimestamp() < now - TEN_SECONDS) {
            weatherDataCache.remove(city.toLowerCase());
            return null;
        }

        return cachedCityData;
    }

    public void save(String city, WeatherData data) {
        weatherDataCache.put(city, data);
    }
}
