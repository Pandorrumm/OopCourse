package ru.nsk.pavlov.view;

import ru.nsk.pavlov.ScaleTemperature;
import ru.nsk.pavlov.controller.Controller;
import ru.nsk.pavlov.model.Converter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DesktopView implements View {
    private final Converter converter;
    private Controller controller;
    private JLabel resultLabel;

    public DesktopView(Converter converter) {
        // TODO всё проверять на null и бросать исключения, так во всех конструкторах
        this.converter = converter;
    }

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Конвертер температуры");

            frame.setSize(500, 250);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(new BorderLayout(10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            // panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JPanel topPanel = new JPanel(new GridLayout(1, 2, 10, 0));

            JPanel fromPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            fromPanel.add(new JLabel("Из:"));
            JComboBox<ScaleTemperature> startingScaleBox = new JComboBox<>(ScaleTemperature.values());
            startingScaleBox.setPreferredSize(new Dimension(120, 25));
            fromPanel.add(startingScaleBox);

            JPanel toPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            toPanel.add(new JLabel("В:"));
            JComboBox<ScaleTemperature> finalScaleBox = new JComboBox<>(ScaleTemperature.values());
            finalScaleBox.setPreferredSize(new Dimension(120, 25));
            toPanel.add(finalScaleBox);

            topPanel.add(fromPanel);
            topPanel.add(toPanel);

            JPanel centerPanel = new JPanel(new GridLayout(2, 2, 10, 10));

            centerPanel.add(new JLabel("Введите значение:"));
            JTextField inputField = new JTextField();
            centerPanel.add(inputField);

            centerPanel.add(new JLabel("Результат:"));
            JTextField resultLabel = new JTextField();
            resultLabel.setEditable(false);
            resultLabel.setBackground(Color.LIGHT_GRAY);
            centerPanel.add(resultLabel);

            JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

            JTextField celsiusTemperatureField = new JTextField();
            celsiusTemperatureField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            //   panel.add(celsiusTemperatureField);

            JButton convertButton = new JButton("Конвертировать");

            bottomPanel.add(convertButton);

            panel.add(topPanel, BorderLayout.NORTH);
            panel.add(centerPanel, BorderLayout.CENTER);
            panel.add(bottomPanel, BorderLayout.SOUTH);

            convertButton.addActionListener(actionEvent -> {
                try {
                    double celsiusTemperature = Double.parseDouble(celsiusTemperatureField.getText());
                    controller.convertToFahrenheit(celsiusTemperature);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame, "Температура должна быть числом", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            });

            startingScaleBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ScaleTemperature selectedStartingScale = (ScaleTemperature) startingScaleBox.getSelectedItem();

                    switch (selectedStartingScale) {
                        case CELSIUS:
                            System.out.println("Из " + selectedStartingScale);
                            break;
                        case FAHRENHEIT:
                            System.out.println("Из " + selectedStartingScale);
                            break;
                        case KELVIN:
                            System.out.println("Из " + selectedStartingScale);
                            break;
                    }
                }
            });

            finalScaleBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ScaleTemperature selectedFinalScale = (ScaleTemperature) finalScaleBox.getSelectedItem();

                    switch (selectedFinalScale) {
                        case CELSIUS:
                            System.out.println("В " + selectedFinalScale);
                            break;
                        case FAHRENHEIT:
                            System.out.println("В " + selectedFinalScale);
                            break;
                        case KELVIN:
                            System.out.println("В " + selectedFinalScale);
                            break;
                    }
                }
            });


            //  panel.add(convertButton);

//            resultLabel = new JLabel();
//            panel.add(resultLabel);

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
    public void temperatureConverted() {
        resultLabel.setText("Температура в шкале Фаренгейта: " + converter.getFahrenheitTemperature());
    }
}
