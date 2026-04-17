package ru.nsk.pavlov.temperature_model;

public interface Converter {
    void convert();

    void setFromScale(TemperatureScale scale);

    void setToScale(TemperatureScale scale);

    void setInputValue(double value);

    double getResult();

    void addConverterListener(ConverterListener listener);
}
