package ru.nsk.pavlov.temperature.model;

import ru.nsk.pavlov.temperature.model.scales.TemperatureScale;

public interface Converter {
    void convert(TemperatureScale fromScale, TemperatureScale toScale, double value);

    double getResult();

    void addConverterListener(ConverterListener listener);
}
