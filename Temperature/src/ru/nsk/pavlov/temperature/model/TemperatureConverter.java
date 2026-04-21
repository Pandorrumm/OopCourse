package ru.nsk.pavlov.temperature.model;

import ru.nsk.pavlov.temperature.model.scales.TemperatureScale;

import java.util.ArrayList;
import java.util.List;

public class TemperatureConverter implements Converter {
    private final List<ConverterListener> listeners = new ArrayList<>();
    private double resultValue;

    @Override
    public void convert(TemperatureScale fromScale, TemperatureScale toScale, double temperature) {
        if (fromScale != null && toScale != null) {
            resultValue = fromScale.convertTo(temperature, toScale);

            notifyListeners(toScale);
        }
    }

    @Override
    public double getResult() {
        return resultValue;
    }

    @Override
    public void addConverterListener(ConverterListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners(TemperatureScale temperatureScale) {
        for (ConverterListener listener : listeners) {
            listener.temperatureConverted(temperatureScale);
        }
    }
}
