package ru.nsk.pavlov.temperature_controller;

import ru.nsk.pavlov.temperature_model.Converter;
import ru.nsk.pavlov.temperature_model.TemperatureScale;
import ru.nsk.pavlov.temperature_view.View;

import java.util.Objects;

public class Controller {
    private final Converter converter;
    private final View view;

    public Controller(Converter converter, View view) {
        this.converter = Objects.requireNonNull(converter, "Converter cannot be null");
        this.view = Objects.requireNonNull(view, "View cannot be null");

        view.setController(this);
        converter.addConverterListener(view);
    }

    public void start() {
        view.start();
    }

    public void convertTemperature(){
      //  converter.convert(celsiusTemperature, TemperatureScale.FAHRENHEIT);
    }

//    public void convertCelsiusToFahrenheit(double celsiusTemperature) {
//       // converter.convertCelsiusToFahrenheit(celsiusTemperature);
//        converter.convert(celsiusTemperature, TemperatureScale.FAHRENHEIT);
//    }
//
//    public void convertCelsiusToKelvin(double celsiusTemperature) {
//    //    converter.convertCelsiusToKelvin(celsiusTemperature);
//        converter.convert(celsiusTemperature, TemperatureScale.KELVIN);
//    }
//
//    public void convertFahrenheitToCelsius(double fahrenheitTemperature) {
//       // converter.convertFahrenheitToCelsius(fahrenheitTemperature);
//        converter.convert(fahrenheitTemperature, TemperatureScale.CELSIUS);
//    }
//
//    public void convertFahrenheitToKelvin(double fahrenheitTemperature) {
//       // converter.convertFahrenheitToKelvin(fahrenheitTemperature);
//        converter.convert(fahrenheitTemperature, TemperatureScale.KELVIN);
//    }
//
//    public void convertKelvinToCelsius(double kelvinTemperature) {
//       // converter.convertKelvinToCelsius(kelvinTemperature);
//        converter.convert(kelvinTemperature, TemperatureScale.CELSIUS);
//    }
//
//    public void convertKelvinToFahrenheit(double kelvinTemperature) {
//       // converter.convertKelvinToFahrenheit(kelvinTemperature);
//        converter.convert(kelvinTemperature, TemperatureScale.FAHRENHEIT);
//    }
//
//    public void convertingIdenticalScales(double temperature, TemperatureScale temperatureScale){
//      //  converter.convertingIdenticalScales(temperature, temperatureScale);
//        converter.convert(temperature, temperatureScale);
//    }
}
