package ru.nsk.pavlov.temperature_view;

import ru.nsk.pavlov.temperature_controller.Controller;
import ru.nsk.pavlov.temperature_model.ConverterListener;

public interface View extends ConverterListener {
    void start();

    void setController(Controller controller);
}
