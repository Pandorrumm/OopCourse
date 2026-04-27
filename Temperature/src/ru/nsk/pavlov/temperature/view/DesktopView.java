package ru.nsk.pavlov.temperature.view;

import ru.nsk.pavlov.temperature.model.scales.TemperatureScale;
import ru.nsk.pavlov.temperature.model.Converter;
import ru.nsk.pavlov.temperature.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.List;

public class DesktopView implements View {
    private final Converter converter;
    private Controller controller;
    private JTextField resultLabel;
    private List<TemperatureScale> availableScales;
    private boolean isStarted;

    public DesktopView(Converter converter) {
        this.converter = Objects.requireNonNull(converter, "Converter cannot be null");
    }

    @Override
    public void start() {
        if (isStarted) {
            throw new IllegalStateException("View has already been started. Cannot start again.");
        }

        isStarted = true;

        Objects.requireNonNull(controller, "Controller cannot be null");

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

            availableScales = converter.getAvailableScales();

            JComboBox<TemperatureScale> fromScaleComboBox = new JComboBox<>();

            for (TemperatureScale availableScale : availableScales) {
                fromScaleComboBox.addItem(availableScale);
            }

            fromScaleComboBox.setPreferredSize(new Dimension(120, 25));

            controller.setFromScale(fromScaleComboBox.getItemAt(0));

            fromPanel.add(fromScaleComboBox);

            JPanel toPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            toPanel.add(new JLabel("To: "));
            JComboBox<TemperatureScale> toScaleComboBox = new JComboBox<>();

            for (TemperatureScale availableScale : availableScales) {
                toScaleComboBox.addItem(availableScale);
            }

            toScaleComboBox.setPreferredSize(new Dimension(120, 25));

            controller.setToScale(toScaleComboBox.getItemAt(0));

            toPanel.add(toScaleComboBox);

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

            fromScaleComboBox.addActionListener(e -> {
                TemperatureScale temperatureScale = (TemperatureScale) fromScaleComboBox.getSelectedItem();

                controller.setFromScale(temperatureScale);
            });

            toScaleComboBox.addActionListener(e -> {
                TemperatureScale temperatureScale = (TemperatureScale) toScaleComboBox.getSelectedItem();

                controller.setToScale(temperatureScale);
            });

            convertButton.addActionListener(actionEvent -> {
                try {
                    double temperature = Double.parseDouble(inputField.getText());

                    controller.setInputTemperature(temperature);
                    controller.convertTemperature();
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
        resultLabel.setText(String.format("%.2f", converter.getConvertedTemperature()));
    }
}