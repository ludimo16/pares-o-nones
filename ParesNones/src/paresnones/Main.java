package paresnones;

/**
 *
 * @author ludimo16
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Main {
    private int wins = 0;
    private int rounds = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame(" Pares o Nones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        Font titleFont = new Font("Arial", Font.BOLD, 24);
        Font normalFont = new Font("Arial", Font.PLAIN, 18);

        // Título
        JLabel titleLabel = new JLabel("Pares o Nones", SwingConstants.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        // Panel de elección Par o Non
        JLabel choiceLabel = new JLabel("Elige Par o Non:", SwingConstants.CENTER);
        choiceLabel.setFont(normalFont);

        JButton parButton = new JButton("Par");
        JButton nonButton = new JButton("Non");
        parButton.setBackground(Color.GREEN);
        nonButton.setBackground(Color.PINK);
        parButton.setFont(normalFont);
        nonButton.setFont(normalFont);
        parButton.setFocusPainted(false);
        nonButton.setFocusPainted(false);

        JPanel choicePanel = new JPanel();
        choicePanel.add(parButton);
        choicePanel.add(nonButton);

        // Entrada de número
        JLabel inputLabel = new JLabel("Introduce un número (0-5):", SwingConstants.CENTER);
        inputLabel.setFont(normalFont);
        JTextField numberField = new JTextField(5);
        numberField.setFont(normalFont);
        numberField.setHorizontalAlignment(JTextField.CENTER);

        // Mensaje de error
        JLabel errorLabel = new JLabel(" ", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);

        // Resultados
        JLabel systemNumberLabel = new JLabel("Número del sistema: ", SwingConstants.CENTER);
        JLabel sumLabel = new JLabel("Suma total: ", SwingConstants.CENTER);
        JLabel resultLabel = new JLabel("Resultado: ", SwingConstants.CENTER);
        JLabel scoreLabel = new JLabel("Rondas: 0 | Victorias: 0", SwingConstants.CENTER);

        JLabel[] labels = {systemNumberLabel, sumLabel, resultLabel, scoreLabel};
        for (JLabel label : labels) {
            label.setFont(normalFont);
        }

        // Botón reiniciar
        JButton resetButton = new JButton(" Reiniciar");
        resetButton.setFont(normalFont);
        resetButton.setBackground(Color.LIGHT_GRAY);
        resetButton.setFocusPainted(false);

        // Paneles organizados
        JPanel centerPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        centerPanel.add(choiceLabel);
        centerPanel.add(choicePanel);
        centerPanel.add(inputLabel);
        centerPanel.add(numberField);
        centerPanel.add(errorLabel);
        centerPanel.add(systemNumberLabel);
        centerPanel.add(sumLabel);
        centerPanel.add(resultLabel);
        centerPanel.add(scoreLabel);

        // Acción de juego
        ActionListener listener = e -> {
            String userChoice = e.getActionCommand();
            int userNumber;

            try {
                userNumber = Integer.parseInt(numberField.getText().trim());
                if (userNumber < 0 || userNumber > 5) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                errorLabel.setText("Introduce un número válido entre 0 y 5.");
                return;
            }

            errorLabel.setText(" "); // Limpia el error
            int systemNumber = new Random().nextInt(6);
            int sum = userNumber + systemNumber;
            boolean isEven = sum % 2 == 0;

            systemNumberLabel.setText("Número del sistema: " + systemNumber);
            sumLabel.setText("Suma total: " + sum);
            boolean userWins = (isEven && userChoice.equals("Par")) || (!isEven && userChoice.equals("Non"));

            resultLabel.setText(userWins ? " ¡Ganaste!" : " Perdiste.");
            rounds++;
            if (userWins) wins++;
            scoreLabel.setText("Rondas: " + rounds + " | Victorias: " + wins);
        };

        parButton.addActionListener(listener);
        nonButton.addActionListener(listener);

        resetButton.addActionListener(e -> {
            wins = 0;
            rounds = 0;
            systemNumberLabel.setText("Número del sistema: ");
            sumLabel.setText("Suma total: ");
            resultLabel.setText("Resultado: ");
            scoreLabel.setText("Rondas: 0 | Victorias: 0");
            numberField.setText("");
            errorLabel.setText(" ");
        });

        // Agregar al frame
        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(resetButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}