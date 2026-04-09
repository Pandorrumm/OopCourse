package ru.nsk.pavlov.model;

public interface Converter {
    void convertToFahrenheit(double celsiusTemperature);

    double getCelsiusTemperature();

    double getFahrenheitTemperature();

    void addConverterListener(ConverterListener listener);
}
