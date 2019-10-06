package pl.bpiotrowski.dto;

import lombok.Getter;

public class WeatherData {
    @Getter String name;
    @Getter Weather[] weather;
}
