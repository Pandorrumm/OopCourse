package ru.nsk.pavlov.temperature.model;

import ru.nsk.pavlov.temperature.model.scales.TemperatureScale;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TemperatureConverter implements Converter {
    private final List<ConverterListener> listeners = new ArrayList<>();
    private final List<TemperatureScale> availableScales;
    private double resultValue;

    public TemperatureConverter(List<TemperatureScale> availableScales) {
        Objects.requireNonNull(availableScales, "Available scales cannot be null");

        if (availableScales.isEmpty()) {
            throw new IllegalArgumentException("Available scales cannot be empty");
        }

        for (int i = 0; i < availableScales.size(); i++) {
            if (availableScales.get(i) == null) {
                throw new IllegalArgumentException("Scale at index " + i + " is null");
            }
        }

        this.availableScales = new ArrayList<>(availableScales);
    }

    public List<TemperatureScale> getAvailableScales() {
        return new ArrayList<>(availableScales);
    }

    @Override
    public void convert(TemperatureScale fromScale, TemperatureScale toScale, double temperature) {
        Objects.requireNonNull(fromScale, "FromScale cannot be null");
        Objects.requireNonNull(toScale, "ToScale cannot be null");

        if (Double.isNaN(temperature)) {
            throw new IllegalArgumentException("The temperature should be a number");
        }

        double celsiusTemperature = fromScale.convertToCelsiusScale(temperature);
        resultValue = toScale.convertFromCelsiusScale(celsiusTemperature);

        notifyListeners(toScale);
    }

    @Override
    public double getResult() {
        return resultValue;
    }

    @Override
    public void addConverterListener(ConverterListener listener) {
        Objects.requireNonNull(listener, "ConverterListener cannot be null");

        listeners.add(listener);
    }

    private void notifyListeners(TemperatureScale temperatureScale) {
        for (ConverterListener listener : listeners) {
            listener.temperatureConverted(temperatureScale);
        }
    }
}
