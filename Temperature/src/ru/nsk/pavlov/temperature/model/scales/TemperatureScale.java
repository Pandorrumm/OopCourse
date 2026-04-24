package ru.nsk.pavlov.temperature.model.scales;

public interface TemperatureScale {
    double convertToCelsiusScale(double temperature);

    double convertFromCelsiusScale(double celsiusTemperature);
}