package ru.nsk.pavlov.temperature.model;

import ru.nsk.pavlov.temperature.model.scales.TemperatureScale;

public interface ConverterListener {
    void temperatureConverted(TemperatureScale temperatureScale);
}
