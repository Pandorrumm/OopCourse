package ru.nsk.pavlov.temperature_model;

public interface TemperatureScale {
    String getName();

    double convertTo(double temperature, TemperatureScale targetScale);
}
