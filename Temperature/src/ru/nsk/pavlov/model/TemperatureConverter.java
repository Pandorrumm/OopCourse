package ru.nsk.pavlov.model;

import java.util.ArrayList;
import java.util.List;

public class TemperatureConverter implements Converter {
    private final List<ConverterListener> listeners = new ArrayList<>();
    private double celsiusTemperature;
    private double fahrenheitTemperature;

    @Override
    public void convertToFahrenheit(double celsiusTemperature) {
        this.celsiusTemperature = celsiusTemperature;
        this.fahrenheitTemperature = celsiusTemperature * 9 / 5 + 32;
        notifyListeners();
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
    public void addConverterListener(ConverterListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (ConverterListener listener : listeners) {
            listener.temperatureConverted();
        }
    }
}
