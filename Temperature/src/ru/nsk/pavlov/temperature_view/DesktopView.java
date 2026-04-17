package ru.nsk.pavlov.temperature_view;

import ru.nsk.pavlov.temperature_model.TemperatureScale;
import ru.nsk.pavlov.temperature_model.Converter;
import ru.nsk.pavlov.temperature_controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class DesktopView implements View {
    private final Converter converter;
    private Controller controller;
    private JTextField resultLabel;
    private TemperatureScale sourceScale = TemperatureScale.NONE;
    private TemperatureScale targetScale = TemperatureScale.NONE;

    public DesktopView(Converter converter) {
        this.converter = Objects.requireNonNull(converter, "Converter cannot be null");
    }

    @Override
    public void start() {
        if (controller == null){
            throw new IllegalArgumentException("The controller is null");
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Temperature Converter");

            frame.setSize(550, 230);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(new BorderLayout(10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JPanel topPanel = new JPanel(new GridLayout(1, 2, 10, 0));

            JPanel fromPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            fromPanel.add(new JLabel("From:"));
            JComboBox<TemperatureScale> sourceScaleBox = new JComboBox<>(TemperatureScale.values());
            sourceScaleBox.setPreferredSize(new Dimension(120, 25));
            fromPanel.add(sourceScaleBox);

            JPanel toPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            toPanel.add(new JLabel("To:"));
            JComboBox<TemperatureScale> targetScaleBox = new JComboBox<>(TemperatureScale.values());
            targetScaleBox.setPreferredSize(new Dimension(120, 25));
            toPanel.add(targetScaleBox);

            topPanel.add(fromPanel);
            topPanel.add(toPanel);

            JPanel centerPanel = new JPanel(new GridLayout(2, 2, -50, 20));

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

            sourceScaleBox.addActionListener(e -> sourceScale = (TemperatureScale) sourceScaleBox.getSelectedItem());
            targetScaleBox.addActionListener(e -> targetScale = (TemperatureScale) targetScaleBox.getSelectedItem());

            convertButton.addActionListener(actionEvent -> {
                try {
                    if (sourceScale == targetScale) {
                        JOptionPane.showMessageDialog(frame, "The temperature scales match", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (sourceScale == TemperatureScale.NONE || targetScale == TemperatureScale.NONE) {
                        JOptionPane.showMessageDialog(frame, "Temperature scale cannot be NONE", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (sourceScale == TemperatureScale.CELSIUS && targetScale == TemperatureScale.FAHRENHEIT) {
                        double celsiusTemperature = Double.parseDouble(inputField.getText());
                        controller.convertCelsiusToFahrenheit(celsiusTemperature);
                    } else if (sourceScale == TemperatureScale.CELSIUS && targetScale == TemperatureScale.KELVIN) {
                        double celsiusTemperature = Double.parseDouble(inputField.getText());
                        controller.convertCelsiusToKelvin(celsiusTemperature);
                    } else if (sourceScale == TemperatureScale.FAHRENHEIT && targetScale == TemperatureScale.CELSIUS) {
                        double fahrenheitTemperature = Double.parseDouble(inputField.getText());
                        controller.convertFahrenheitToCelsius(fahrenheitTemperature);
                    } else if (sourceScale == TemperatureScale.FAHRENHEIT && targetScale == TemperatureScale.KELVIN) {
                        double fahrenheitTemperature = Double.parseDouble(inputField.getText());
                        controller.convertFahrenheitToKelvin(fahrenheitTemperature);
                    } else if (sourceScale == TemperatureScale.KELVIN && targetScale == TemperatureScale.CELSIUS) {
                        double kelvinTemperature = Double.parseDouble(inputField.getText());
                        controller.convertKelvinToCelsius(kelvinTemperature);
                    } else if (sourceScale == TemperatureScale.KELVIN && targetScale == TemperatureScale.FAHRENHEIT) {
                        double kelvinTemperature = Double.parseDouble(inputField.getText());
                        controller.convertKelvinToFahrenheit(kelvinTemperature);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame, "The temperature should be a number", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            frame.add(panel);
            frame.setResizable(false);
            frame.setVisible(true);
        });
    }

    @Override
    public void setController(Controller controller) {
        this.controller = Objects.requireNonNull(controller, "Controller cannot be null");
    }

    @Override
    public void temperatureConverted(TemperatureScale temperatureScale) {
        switch (temperatureScale) {
            case CELSIUS:
                resultLabel.setText(String.format("%.2f", converter.getCelsiusTemperature()));
                break;
            case FAHRENHEIT:
                resultLabel.setText(String.format("%.2f", converter.getFahrenheitTemperature()));
                break;
            case KELVIN:
                resultLabel.setText(String.format("%.2f", converter.getKelvinTemperature()));
                break;
        }
    }
}