package ru.nsk.pavlov.temperature.model.scales;

public interface TemperatureScale {
    String getName();

    double convertToCelsiusScale(double temperature);

    double convertFromCelsiusScale(double celsiusTemperature);
}