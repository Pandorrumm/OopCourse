package ru.nsk.pavlov.model;

import ru.nsk.pavlov.ScaleTemperature;

import java.util.ArrayList;
import java.util.List;

public class TemperatureConverter implements Converter {
    private final List<ConverterListener> listeners = new ArrayList<>();
    private double celsiusTemperature;
    private double fahrenheitTemperature;
    private double kelvinTemperature;

    @Override
    public void convertCelsiusToFahrenheit(double celsiusTemperature) {
        this.celsiusTemperature = celsiusTemperature;
        this.fahrenheitTemperature = celsiusTemperature * 9 / 5 + 32;
        notifyListeners(ScaleTemperature.FAHRENHEIT);
    }

    @Override
    public void convertCelsiusToKelvin(double celsiusTemperature) {
        this.celsiusTemperature = celsiusTemperature;
        this.kelvinTemperature = celsiusTemperature + 273.15;
        notifyListeners(ScaleTemperature.KELVIN);
    }

    @Override
    public void convertFahrenheitToCelsius(double fahrenheitTemperature) {
        this.fahrenheitTemperature = fahrenheitTemperature;
        this.celsiusTemperature = (fahrenheitTemperature - 32) * 5 / 9;
        notifyListeners(ScaleTemperature.CELSIUS);
    }

    @Override
    public void convertFahrenheitToKelvin(double fahrenheitTemperature) {
        this.fahrenheitTemperature = fahrenheitTemperature;
        this.kelvinTemperature = (fahrenheitTemperature - 32) * 5 / 9 + 273.15;
        notifyListeners(ScaleTemperature.KELVIN);
    }

    @Override
    public void convertKelvinToCelsius(double kelvinTemperature) {
        this.kelvinTemperature = kelvinTemperature;
        this.celsiusTemperature = kelvinTemperature - 273.15;
        notifyListeners(ScaleTemperature.CELSIUS);
    }

    @Override
    public void convertKelvinToFahrenheit(double kelvinTemperature) {
        this.kelvinTemperature = kelvinTemperature;
        this.fahrenheitTemperature = (kelvinTemperature - 273.15) * 9 / 5 + 32;
        notifyListeners(ScaleTemperature.FAHRENHEIT);
    }

    @Override
    public double getCelsiusTemperature() {
        return celsiusTemperature;
    }

    @Override
    public double getFahrenheitTemperature() {
        return fahrenheitTemperature;
    }

    @Override
    public double getKelvinTemperature() {
        return kelvinTemperature;
    }

    @Override
    public void addConverterListener(ConverterListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners(ScaleTemperature scaleTemperature) {
        for (ConverterListener listener : listeners) {
            listener.temperatureConverted(scaleTemperature);
        }
    }
}
