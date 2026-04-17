package ru.nsk.pavlov.temperature_model;

public class CelsiusScale implements TemperatureScale {

    @Override
    public String getName() {
        return "Celsius";
    }

    @Override
    public double convertTo(double temperature, TemperatureScale targetScale) {
        String targetScaleName = targetScale.getName();

        return switch (targetScaleName) {
            case "Fahrenheit" -> temperature * 9.0 / 5.0 + 32;
            case "Kelvin" -> temperature + 273.15;
            default -> temperature;
        };
    }
}
