package ru.nsk.pavlov.view;

import ru.nsk.pavlov.ScaleTemperature;
import ru.nsk.pavlov.controller.Controller;
import ru.nsk.pavlov.model.Converter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class DesktopView implements View {
    private final Converter converter;
    private Controller controller;
    private JTextField resultLabel;
    private ScaleTemperature selectedStartingScale = ScaleTemperature.NONE;
    private ScaleTemperature selectedFinalScale = ScaleTemperature.NONE;

    public DesktopView(Converter converter) {
        this.converter = Objects.requireNonNull(converter, "Converter cannot be null");
    }

    @Override
    public void start() {
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
            JComboBox<ScaleTemperature> startingScaleBox = new JComboBox<>(ScaleTemperature.values());
            startingScaleBox.setPreferredSize(new Dimension(120, 25));
            fromPanel.add(startingScaleBox);

            JPanel toPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            toPanel.add(new JLabel("To:"));
            JComboBox<ScaleTemperature> finalScaleBox = new JComboBox<>(ScaleTemperature.values());
            finalScaleBox.setPreferredSize(new Dimension(120, 25));
            toPanel.add(finalScaleBox);

            topPanel.add(fromPanel);
            topPanel.add(toPanel);

            JPanel centerPanel = new JPanel(new GridLayout(2, 2, -50, 20));

            centerPanel.add(new JLabel("Enter the value:"));
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

            startingScaleBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedStartingScale = (ScaleTemperature) startingScaleBox.getSelectedItem();
                }
            });

            finalScaleBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedFinalScale = (ScaleTemperature) finalScaleBox.getSelectedItem();
                }
            });

            convertButton.addActionListener(actionEvent -> {
                try {
                    if (selectedStartingScale == selectedFinalScale) {
                        JOptionPane.showMessageDialog(frame, "The temperature scales match", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (selectedStartingScale == ScaleTemperature.NONE || selectedFinalScale == ScaleTemperature.NONE) {
                        JOptionPane.showMessageDialog(frame, "Temperature scale cannot be NONE", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (selectedStartingScale == ScaleTemperature.CELSIUS && selectedFinalScale == ScaleTemperature.FAHRENHEIT) {
                        double celsiusTemperature = Double.parseDouble(inputField.getText());
                        controller.convertCelsiusToFahrenheit(celsiusTemperature);
                    } else if (selectedStartingScale == ScaleTemperature.CELSIUS && selectedFinalScale == ScaleTemperature.KELVIN) {
                        double celsiusTemperature = Double.parseDouble(inputField.getText());
                        controller.convertCelsiusToKelvin(celsiusTemperature);
                    } else if (selectedStartingScale == ScaleTemperature.FAHRENHEIT && selectedFinalScale == ScaleTemperature.CELSIUS) {
                        double fahrenheitTemperature = Double.parseDouble(inputField.getText());
                        controller.convertFahrenheitToCelsius(fahrenheitTemperature);
                    } else if (selectedStartingScale == ScaleTemperature.FAHRENHEIT && selectedFinalScale == ScaleTemperature.KELVIN) {
                        double fahrenheitTemperature = Double.parseDouble(inputField.getText());
                        controller.convertFahrenheitToKelvin(fahrenheitTemperature);
                    } else if (selectedStartingScale == ScaleTemperature.KELVIN && selectedFinalScale == ScaleTemperature.CELSIUS) {
                        double kelvinTemperature = Double.parseDouble(inputField.getText());
                        controller.convertKelvinToCelsius(kelvinTemperature);
                    } else if (selectedStartingScale == ScaleTemperature.KELVIN && selectedFinalScale == ScaleTemperature.FAHRENHEIT) {
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
        this.controller = controller;
    }

    @Override
    public void temperatureConverted(ScaleTemperature scaleTemperature) {
        switch (scaleTemperature) {
            case CELSIUS:
                resultLabel.setText("Temperature in Celsius scale: " + Math.round(converter.getCelsiusTemperature() * 10.0) / 10.0);
                break;
            case FAHRENHEIT:
                resultLabel.setText("Temperature in the Fahrenheit scale: " + Math.round(converter.getFahrenheitTemperature() * 10.0) / 10.0);
                break;
            case KELVIN:
                resultLabel.setText("Temperature in the Kelvin scale: " + Math.round(converter.getKelvinTemperature() * 10.0) / 10.0);
                break;
        }
    }
}