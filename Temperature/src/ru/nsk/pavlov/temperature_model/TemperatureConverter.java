package ru.nsk.pavlov.temperature_model;

import java.util.ArrayList;
import java.util.List;

public class TemperatureConverter implements Converter {
    private final List<ConverterListener> listeners = new ArrayList<>();

    private TemperatureScale fromScale;
    private TemperatureScale toScale;

    private double inputValue;
    private double resultValue;

    @Override
    public void setFromScale(TemperatureScale scale) {
        fromScale = scale;
    }

    @Override
    public void setToScale(TemperatureScale scale) {
        toScale = scale;
    }

    public void setInputValue(double value) {
        inputValue = value;
    }

    @Override
    public void convert() {
        if (fromScale != null && toScale != null) {
            resultValue = fromScale.convertTo(inputValue, toScale);

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
