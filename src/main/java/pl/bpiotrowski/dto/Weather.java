package pl.bpiotrowski.dto;

import lombok.Getter;

public class Weather {
    int id;
    String main;
    @Getter String description;
    String icon;
}
