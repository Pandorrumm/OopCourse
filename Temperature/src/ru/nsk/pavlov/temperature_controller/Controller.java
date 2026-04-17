package ru.nsk.pavlov.temperature_controller;

import ru.nsk.pavlov.temperature_model.Converter;
import ru.nsk.pavlov.temperature_view.View;

import java.util.Objects;

public class Controller {
    private final Converter converter;
    private final View view;

    public Controller(Converter converter, View view) {
        this.converter = Objects.requireNonNull(converter, "Converter cannot be null");
        this.view = Objects.requireNonNull(view, "View cannot be null");

        view.setController(this);
        converter.addConverterListener(view);
    }

    public void start() {
        view.start();
    }

    public void convertCelsiusToFahrenheit(double celsiusTemperature) {
        converter.convertCelsiusToFahrenheit(celsiusTemperature);
    }

    public void convertCelsiusToKelvin(double celsiusTemperature) {
        converter.convertCelsiusToKelvin(celsiusTemperature);
    }

    public void convertFahrenheitToCelsius(double fahrenheitTemperature) {
        converter.convertFahrenheitToCelsius(fahrenheitTemperature);
    }

    public void convertFahrenheitToKelvin(double fahrenheitTemperature) {
        converter.convertFahrenheitToKelvin(fahrenheitTemperature);
    }

    public void convertKelvinToCelsius(double kelvinTemperature) {
        converter.convertKelvinToCelsius(kelvinTemperature);
    }

    public void convertKelvinToFahrenheit(double kelvinTemperature) {
        converter.convertKelvinToFahrenheit(kelvinTemperature);
    }
}
