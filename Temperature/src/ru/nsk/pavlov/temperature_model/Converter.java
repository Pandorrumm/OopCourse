package ru.nsk.pavlov.temperature_model;

public interface Converter {
    void convert(TemperatureScale fromScale, TemperatureScale toScale, double value);

    double getResult();

    void addConverterListener(ConverterListener listener);
}
