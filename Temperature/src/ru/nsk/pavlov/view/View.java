package ru.nsk.pavlov.view;

import ru.nsk.pavlov.controller.Controller;
import ru.nsk.pavlov.model.ConverterListener;

public interface View extends ConverterListener {
    void start();

    void setController(Controller controller);
}
