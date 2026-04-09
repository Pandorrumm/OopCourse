package ru.nsk.pavlov.view;

import ru.nsk.pavlov.controller.Controller;
import ru.nsk.pavlov.model.Converter;

import javax.swing.*;
import java.awt.*;

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

            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JLabel enterTemperatureLabel = new JLabel("Введите температуру:");
            panel.add(enterTemperatureLabel);

            JTextField celsiusTemperatureField = new JTextField();
            celsiusTemperatureField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            panel.add(celsiusTemperatureField);

            JButton convertButton = new JButton("Конвертировать");

            convertButton.addActionListener(actionEvent -> {
                try {
                    double celsiusTemperature = Double.parseDouble(celsiusTemperatureField.getText());
                    controller.convertToFahrenheit(celsiusTemperature);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame, "Температура должна быть числом", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            });

            panel.add(convertButton);

            resultLabel = new JLabel();
            panel.add(resultLabel);

            frame.add(panel);

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
