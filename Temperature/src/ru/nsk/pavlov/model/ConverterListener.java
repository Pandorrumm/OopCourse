package ru.nsk.pavlov.model;

import ru.nsk.pavlov.ScaleTemperature;

public interface ConverterListener {
    void temperatureConverted(ScaleTemperature scaleTemperature);
}
