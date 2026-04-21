package ru.nsk.pavlov.temperature.main;

import ru.nsk.pavlov.temperature.controller.Controller;
import ru.nsk.pavlov.temperature.model.*;
import ru.nsk.pavlov.temperature.model.scales.CelsiusScale;
import ru.nsk.pavlov.temperature.model.scales.FahrenheitScale;
import ru.nsk.pavlov.temperature.model.scales.KelvinScale;
import ru.nsk.pavlov.temperature.model.scales.TemperatureScale;
import ru.nsk.pavlov.temperature.view.DesktopView;
import ru.nsk.pavlov.temperature.view.View;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<TemperatureScale> scales = Arrays.asList(
                new CelsiusScale(),
                new FahrenheitScale(),
                new KelvinScale()
        );

        Converter converter = new TemperatureConverter(scales);
        View view = new DesktopView(converter, scales);
        Controller controller = new Controller(converter, view);
        controller.start();
    }
}
