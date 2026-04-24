package ru.nsk.pavlov.temperature.model.scales;

public class FahrenheitScale implements TemperatureScale {
    @Override
    public double convertToCelsiusScale(double temperature) {
        return (temperature - 32) * 5.0 / 9.0;
    }

    @Override
    public double convertFromCelsiusScale(double celsiusTemperature) {
        return celsiusTemperature * 9.0 / 5.0 + 32;
    }

    @Override
    public String toString(){
        return "Fahrenheit";
    }
}
