package ru.nsk.pavlov.temperature_controller;

import ru.nsk.pavlov.temperature_model.Converter;
import ru.nsk.pavlov.temperature_model.TemperatureScale;
import ru.nsk.pavlov.temperature_view.View;

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
        fromScale = scale;
    }

    public void setToScale(TemperatureScale scale) {
        toScale = scale;
    }

    public void setInputValue(double value) {
        inputValue = value;
    }

    public void convertTemperature() {
        converter.convert(fromScale, toScale, inputValue);
    }
}
