package ru.nsk.pavlov;

import ru.nsk.pavlov.temperature_controller.Controller;
import ru.nsk.pavlov.temperature_model.*;
import ru.nsk.pavlov.temperature_view.DesktopView;
import ru.nsk.pavlov.temperature_view.View;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<TemperatureScale> scales = Arrays.asList(
                new CelsiusScale(),
                new FahrenheitScale(),
                new KelvinScale()
        );

        Converter converter = new TemperatureConverter();
        View view = new DesktopView(converter, scales);
        Controller controller = new Controller(converter, view);
        controller.start();
    }
}
