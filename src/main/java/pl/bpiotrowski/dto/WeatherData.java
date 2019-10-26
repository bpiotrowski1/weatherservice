package pl.bpiotrowski.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class WeatherData {
    String name;
    Weather[] weather;
    @Setter Long timestamp;
}
