package ru.nsk.pavlov.temperature.model.scales;

public class KelvinScale implements TemperatureScale {
    @Override
    public double convertToCelsiusScale(double temperature) {
        return temperature - 273.15;
    }

    @Override
    public double convertFromCelsiusScale(double celsiusTemperature) {
        return celsiusTemperature + 273.15;
    }

    @Override
    public String toString(){
        return "Kelvin";
    }
}
