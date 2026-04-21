package ru.nsk.pavlov.temperature.model;

import ru.nsk.pavlov.temperature.model.scales.TemperatureScale;

import java.util.List;

public interface Converter {
    void convert(TemperatureScale fromScale, TemperatureScale toScale, double temperature);

    double getResult();

    void addConverterListener(ConverterListener listener);

    List<TemperatureScale> getAvailableScales();
}
