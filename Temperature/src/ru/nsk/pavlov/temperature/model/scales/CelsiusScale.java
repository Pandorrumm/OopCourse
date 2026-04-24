package ru.nsk.pavlov.temperature.model.scales;

public class CelsiusScale implements TemperatureScale {
    @Override
    public double convertToCelsiusScale(double temperature) {
        return temperature;
    }

    @Override
    public double convertFromCelsiusScale(double celsiusTemperature) {
        return celsiusTemperature;
    }

    @Override
    public String toString(){
        return "Celsius";
    }
}
