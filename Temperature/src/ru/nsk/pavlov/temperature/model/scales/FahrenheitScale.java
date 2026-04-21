package ru.nsk.pavlov.temperature.model.scales;

public class FahrenheitScale implements TemperatureScale {

    @Override
    public String getName() {
        return "Fahrenheit";
    }

    @Override
    public double convertTo(double temperature, TemperatureScale targetScale) {
        String targetScaleName = targetScale.getName();

        return switch (targetScaleName) {
            case "Celsius" -> (temperature - 32) * 5.0 / 9.0;
            case "Kelvin" -> (temperature - 32) * 5.0 / 9.0 + 273.15;
            default -> temperature;
        };
    }
}
