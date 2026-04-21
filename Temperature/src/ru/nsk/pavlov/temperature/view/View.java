package ru.nsk.pavlov.temperature.view;

import ru.nsk.pavlov.temperature.controller.Controller;
import ru.nsk.pavlov.temperature.model.ConverterListener;

public interface View extends ConverterListener {
    void start();

    void setController(Controller controller);
}
