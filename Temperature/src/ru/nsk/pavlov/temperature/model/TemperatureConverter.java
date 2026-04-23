package ru.nsk.pavlov.temperature.model;

import ru.nsk.pavlov.temperature.model.scales.TemperatureScale;

import java.util.ArrayList;
import java.util.List;

public class TemperatureConverter implements Converter {
    private final List<ConverterListener> listeners = new ArrayList<>();
    private final List<TemperatureScale> availableScales;
    private double resultValue;

    public TemperatureConverter(List<TemperatureScale> availableScales) {
        if (availableScales == null || availableScales.isEmpty()) {
            throw new IllegalArgumentException("Available scales cannot be null");
        }

        for (int i = 0; i < availableScales.size(); i++) {
            if (availableScales.get(i) == null) {
                throw new IllegalArgumentException("Scale at index " + i + " is null");
            }
        }

        this.availableScales = new ArrayList<>(availableScales);
    }

    public List<TemperatureScale> getAvailableScales() {
        return availableScales;
    }

    @Override
    public void convert(TemperatureScale fromScale, TemperatureScale toScale, double temperature) {
        if (fromScale == null) {
            throw new IllegalArgumentException("FromScale cannot be null");
        }

        if (toScale == null) {
            throw new IllegalArgumentException("ToScale cannot be null");
        }

        if (Double.isNaN(temperature)) {
            throw new IllegalArgumentException("The temperature should be a number");
        }

        double celsiusTemperature = fromScale.toCelsiusScale(temperature);
        resultValue = toScale.fromCelsiusScale(celsiusTemperature);

        notifyListeners(toScale);
    }

    @Override
    public double getResult() {
        return resultValue;
    }

    @Override
    public void addConverterListener(ConverterListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("ConverterListener cannot be null");
        }

        listeners.add(listener);
    }

    private void notifyListeners(TemperatureScale temperatureScale) {
        for (ConverterListener listener : listeners) {
            listener.temperatureConverted(temperatureScale);
        }
    }
}
