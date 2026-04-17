package ru.nsk.pavlov;

import ru.nsk.pavlov.temperature_controller.Controller;
import ru.nsk.pavlov.temperature_model.Converter;
import ru.nsk.pavlov.temperature_model.TemperatureConverter;
import ru.nsk.pavlov.temperature_view.DesktopView;
import ru.nsk.pavlov.temperature_view.View;

public class Main {
    public static void main(String[] args) {
        Converter converter = new TemperatureConverter();
        View view = new DesktopView(converter);
        Controller controller = new Controller(converter, view);
        controller.start();
    }
}
