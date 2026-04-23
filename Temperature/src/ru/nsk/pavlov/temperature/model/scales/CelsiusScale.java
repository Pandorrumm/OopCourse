package ru.nsk.pavlov.temperature.model.scales;

public class CelsiusScale implements TemperatureScale {
    @Override
    public String getName() {
        return "Celsius";
    }

    @Override
    public double toCelsiusScale(double temperature) {
        return temperature;
    }

    @Override
    public double fromCelsiusScale(double celsiusTemperature) {
        return celsiusTemperature;
    }
}
