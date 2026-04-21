package ru.nsk.pavlov.temperature.model.scales;

public class KelvinScale implements TemperatureScale {
    @Override
    public String getName() {
        return "Kelvin";
    }

    @Override
    public double convertTo(double temperature, TemperatureScale targetScale) {
        String targetScaleName = targetScale.getName();

        return switch (targetScaleName) {
            case "Celsius" -> temperature - 273.15;
            case "Fahrenheit" -> (temperature - 273.15) * 9.0 / 5.0 + 32;
            default -> temperature;
        };
    }
}
