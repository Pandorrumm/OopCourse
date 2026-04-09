package ru.nsk.pavlov.controller;

import ru.nsk.pavlov.model.Converter;
import ru.nsk.pavlov.view.View;

public class Controller {
    private final Converter converter;
    private final View view;

    public Controller(Converter converter, View view) {
        this.converter = converter;
        this.view = view;

        view.setController(this);
        converter.addConverterListener(view);
    }

    public void start() {
        view.start();
    }

    public void convertToFahrenheit(double celsiusTemperature) {
        converter.convertToFahrenheit(celsiusTemperature);
    }
}
