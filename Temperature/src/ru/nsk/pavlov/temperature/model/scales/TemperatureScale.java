package ru.nsk.pavlov.temperature.model.scales;

public interface TemperatureScale {
    String getName();

    double toCelsiusScale(double temperature);

    double fromCelsiusScale(double celsiusTemperature);
}