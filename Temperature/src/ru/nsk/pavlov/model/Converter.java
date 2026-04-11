package ru.nsk.pavlov.model;

public interface Converter {
    void convertCelsiusToFahrenheit(double celsiusTemperature);

    void convertCelsiusToKelvin(double celsiusTemperature);

    void convertFahrenheitToCelsius(double fahrenheitTemperature);

    void convertFahrenheitToKelvin(double fahrenheitTemperature);

    void convertKelvinToCelsius(double kelvinTemperature);

    void convertKelvinToFahrenheit(double kelvinTemperature);

    double getCelsiusTemperature();

    double getFahrenheitTemperature();

    double getKelvinTemperature();

    void addConverterListener(ConverterListener listener);
}
