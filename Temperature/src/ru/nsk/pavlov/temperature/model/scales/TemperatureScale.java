package ru.nsk.pavlov.temperature.model.scales;

public interface TemperatureScale {
    String getName();

    double convertTo(double temperature, TemperatureScale targetScale);
}
