package ru.nsk.pavlov.temperature.controller;

import ru.nsk.pavlov.temperature.model.Converter;
import ru.nsk.pavlov.temperature.model.scales.TemperatureScale;
import ru.nsk.pavlov.temperature.view.View;

import java.util.Objects;

public class Controller {
    private final Converter converter;
    private final View view;

    private TemperatureScale fromScale;
    private TemperatureScale toScale;

    private double inputValue;

    public Controller(Converter converter, View view) {
        this.converter = Objects.requireNonNull(converter, "Converter cannot be null");
        this.view = Objects.requireNonNull(view, "View cannot be null");

        view.setController(this);
        converter.addConverterListener(view);
    }

    public void start() {
        view.start();
    }

    public void setFromScale(TemperatureScale scale) {
        fromScale = Objects.requireNonNull(scale, "From scale cannot be null");
    }

    public void setToScale(TemperatureScale scale) {
        toScale = Objects.requireNonNull(scale, "To scale cannot be null");
    }

    public void setInputValue(double inputValue) {
        this.inputValue = inputValue;
    }

    public void convertTemperature() {
        converter.convert(fromScale, toScale, inputValue);
    }
}
