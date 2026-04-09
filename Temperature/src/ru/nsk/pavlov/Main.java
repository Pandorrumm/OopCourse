package ru.nsk.pavlov;

import ru.nsk.pavlov.controller.Controller;
import ru.nsk.pavlov.model.Converter;
import ru.nsk.pavlov.model.TemperatureConverter;
import ru.nsk.pavlov.view.DesktopView;
import ru.nsk.pavlov.view.View;

public class Main {
    public static void main(String[] args) {
        Converter converter = new TemperatureConverter();
        View view = new DesktopView(converter);
        Controller controller = new Controller(converter, view);
        controller.start();
    }
}
