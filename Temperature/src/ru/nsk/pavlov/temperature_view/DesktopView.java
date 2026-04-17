package ru.nsk.pavlov.temperature_view;

import ru.nsk.pavlov.temperature_model.TemperatureScale;
import ru.nsk.pavlov.temperature_model.Converter;
import ru.nsk.pavlov.temperature_controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

public class DesktopView implements View {
    private final Converter converter;
    private Controller controller;
    private JTextField resultLabel;
    private TemperatureScale sourceScale;
    private TemperatureScale targetScale;
    private final List<TemperatureScale> availableScales;

    public DesktopView(Converter converter, List<TemperatureScale> availableScales) {
        this.converter = Objects.requireNonNull(converter, "Converter cannot be null");

        this.availableScales = new ArrayList<>(availableScales);

        if (!this.availableScales.isEmpty()){
            sourceScale = this.availableScales.getFirst();
            targetScale = this.availableScales.getFirst();
        }
    }

    public List<TemperatureScale> getAvailableScales(){
        return new ArrayList<>(availableScales);
    }

    @Override
    public void start() {
        if (controller == null) {
            throw new IllegalArgumentException("The controller is null");
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Temperature Converter");

            frame.setSize(550, 230);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(new BorderLayout(10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JPanel topPanel = new JPanel(new GridLayout(1, 2));

            JPanel fromPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            fromPanel.add(new JLabel("From: "));
            JComboBox<String> sourceScaleBox = new JComboBox<>();

            for (TemperatureScale availableScale : availableScales) {
                sourceScaleBox.addItem(availableScale.getName());
            }

            sourceScaleBox.setPreferredSize(new Dimension(120, 25));
            fromPanel.add(sourceScaleBox);

            JPanel toPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            toPanel.add(new JLabel("To: "));
            JComboBox<String> targetScaleBox = new JComboBox<>();

            for (TemperatureScale availableScale : availableScales) {
                targetScaleBox.addItem(availableScale.getName());
            }

            targetScaleBox.setPreferredSize(new Dimension(120, 25));
            toPanel.add(targetScaleBox);

            topPanel.add(fromPanel);
            topPanel.add(toPanel);

            JPanel centerPanel = new JPanel(new GridLayout(2, 2, 0, 20));

            centerPanel.add(new JLabel("Enter the temperature:"));
            JTextField inputField = new JTextField();
            centerPanel.add(inputField);

            centerPanel.add(new JLabel("Result:"));
            resultLabel = new JTextField();
            resultLabel.setEditable(false);
            resultLabel.setBackground(Color.LIGHT_GRAY);
            centerPanel.add(resultLabel);

            JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

            JButton convertButton = new JButton("Convert");

            bottomPanel.add(convertButton);

            panel.add(topPanel, BorderLayout.NORTH);
            panel.add(centerPanel, BorderLayout.CENTER);
            panel.add(bottomPanel, BorderLayout.SOUTH);

            sourceScaleBox.addActionListener(e -> {
                String selectedScale = (String) sourceScaleBox.getSelectedItem();

                TemperatureScale temperatureScale = getScaleByName(selectedScale);

                if (temperatureScale != null){
                    converter.setFromScale(temperatureScale);
                }
            });

            targetScaleBox.addActionListener(e -> {
                String selectedScale = (String) targetScaleBox.getSelectedItem();

                TemperatureScale temperatureScale = getScaleByName(selectedScale);

                if (temperatureScale != null){
                    converter.setToScale(temperatureScale);
                }
            });

            convertButton.addActionListener(actionEvent -> {
                try {
                    double temperature = Double.parseDouble(inputField.getText());

                    converter.setInputValue(temperature);
                    converter.convert();

//                    if (sourceScale == targetScale) {
//                        double temperature = Double.parseDouble(inputField.getText());
//                        controller.convertingIdenticalScales(temperature, sourceScale);
//                    } else if (sourceScale == TemperatureScale.CELSIUS && targetScale == TemperatureScale.FAHRENHEIT) {
//                        double celsiusTemperature = Double.parseDouble(inputField.getText());
//                        controller.convertCelsiusToFahrenheit(celsiusTemperature);
//                    } else if (sourceScale == TemperatureScale.CELSIUS && targetScale == TemperatureScale.KELVIN) {
//                        double celsiusTemperature = Double.parseDouble(inputField.getText());
//                        controller.convertCelsiusToKelvin(celsiusTemperature);
//                    } else if (sourceScale == TemperatureScale.FAHRENHEIT && targetScale == TemperatureScale.CELSIUS) {
//                        double fahrenheitTemperature = Double.parseDouble(inputField.getText());
//                        controller.convertFahrenheitToCelsius(fahrenheitTemperature);
//                    } else if (sourceScale == TemperatureScale.FAHRENHEIT && targetScale == TemperatureScale.KELVIN) {
//                        double fahrenheitTemperature = Double.parseDouble(inputField.getText());
//                        controller.convertFahrenheitToKelvin(fahrenheitTemperature);
//                    } else if (sourceScale == TemperatureScale.KELVIN && targetScale == TemperatureScale.CELSIUS) {
//                        double kelvinTemperature = Double.parseDouble(inputField.getText());
//                        controller.convertKelvinToCelsius(kelvinTemperature);
//                    } else if (sourceScale == TemperatureScale.KELVIN && targetScale == TemperatureScale.FAHRENHEIT) {
//                        double kelvinTemperature = Double.parseDouble(inputField.getText());
//                        controller.convertKelvinToFahrenheit(kelvinTemperature);
//                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame, "The temperature should be a number", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            frame.add(panel);
            frame.setResizable(false);
            frame.setVisible(true);
        });
    }

    public TemperatureScale getScaleByName(String nameScale) {
        for (TemperatureScale scale : availableScales) {
            if (scale.getName().equals(nameScale)) {
                return scale;
            }
        }

        return null;
    }

    @Override
    public void setController(Controller controller) {
        this.controller = Objects.requireNonNull(controller, "Controller cannot be null");
    }

    @Override
    public void temperatureConverted(TemperatureScale temperatureScale) {
        resultLabel.setText(String.format("%.2f", converter.getResult()));
    }
}