package ru.nsk.pavlov.temperature.model.scales;

public class KelvinScale implements TemperatureScale {
    @Override
    public String getName() {
        return "Kelvin";
    }

    @Override
    public double toCelsiusScale(double temperature) {
        return temperature - 273.15;
    }

    @Override
    public double fromCelsiusScale(double celsiusTemperature) {
        return celsiusTemperature + 273.15;
    }
}
