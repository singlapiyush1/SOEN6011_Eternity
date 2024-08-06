import javax.swing.*;
import java.awt.*;

public class PowerCalculatorGUI extends JFrame {

    private JTextField baseField;
    private JTextField exponentField;
    private JLabel resultLabel;

    public PowerCalculatorGUI() {
        // Setup the frame
        setTitle("Power Function Calculator");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create main panel with padding and background color
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(0xFFF8DC));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // Create calculator panel with padding, border, and shadow
        JPanel calculatorPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        calculatorPanel.setBackground(Color.WHITE);
        calculatorPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1, true),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        calculatorPanel.setOpaque(true);

        // Create UI components with styled labels and text fields
        JLabel baseLabel = new JLabel("Base (x):");
        String font = "Arial";
        baseLabel.setFont(new Font(font, Font.PLAIN, 14));
        baseField = new JTextField();
        baseField.setFont(new Font(font, Font.PLAIN, 14));

        JLabel exponentLabel = new JLabel("Exponent (y):");
        exponentLabel.setFont(new Font(font, Font.PLAIN, 14));
        exponentField = new JTextField();
        exponentField.setFont(new Font(font, Font.PLAIN, 14));

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setFont(new Font(font, Font.BOLD, 14));
        calculateButton.setBackground(new Color(0x7860DE));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);
        calculateButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0x8C51E5)),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        calculateButton.setOpaque(true);

        resultLabel = new JLabel("Result: ");
        resultLabel.setFont(new Font(font, Font.PLAIN, 14));
        resultLabel.setOpaque(true);
        resultLabel.setBackground(new Color(0xD3D3D3));
        resultLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Add components to the calculator panel
        calculatorPanel.add(baseLabel);
        calculatorPanel.add(baseField);
        calculatorPanel.add(exponentLabel);
        calculatorPanel.add(exponentField);
        calculatorPanel.add(calculateButton);

        // Add panels to the main panel
        mainPanel.add(calculatorPanel, BorderLayout.CENTER);
        mainPanel.add(resultLabel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(mainPanel);

        // Add action listener to the button
        calculateButton.addActionListener(e -> calculatePower());
    }

    private void calculatePower() {
        try {
            if (baseField.getText().isEmpty()) {
                throw new IllegalArgumentException("Error: Base (x) is required.");
            }
            if (exponentField.getText().isEmpty()) {
                throw new IllegalArgumentException("Error: Exponent (y) is required.");
            }

            double base = Double.parseDouble(baseField.getText());
            double exponent = Double.parseDouble(exponentField.getText());
            double result = pow(base, exponent);
            resultLabel.setText("Result: " + String.format("%.10f", result));
            resultLabel.setForeground(Color.BLACK);
        } catch (NumberFormatException e) {
            resultLabel.setText("Error: Please enter valid numbers for base and exponent.");
            resultLabel.setForeground(Color.RED);
        } catch (IllegalArgumentException e) {
            resultLabel.setText(e.getMessage());
            resultLabel.setForeground(Color.RED);
        } catch (Exception e) {
            resultLabel.setText("An unexpected error occurred: " + e.getMessage());
            resultLabel.setForeground(Color.RED);
        }
    }

    public static double pow(double base, double exponent) {
        if (exponent == 0) {
            return 1;
        }
        if (base == 0) {
            return 0;
        }

        int intPart = (int) exponent;
        double fracPart = exponent - intPart;

        double intPartResult = powInt(base, intPart);
        double fracPartResult = powFrac(base, fracPart);

        return intPartResult * fracPartResult;
    }

    private static double powInt(double base, int exponent) {
        if (exponent < 0) {
            base = 1 / base;
            exponent = -exponent;
        }
        double result = 1;
        while (exponent > 0) {
            if ((exponent % 2) == 1) {
                result *= base;
            }
            base *= base;
            exponent /= 2;
        }
        return result;
    }

    private static double powFrac(double base, double exponent) {
        double lnBase = ln(base);
        double x = exponent * lnBase;
        return exp(x);
    }

    private static double ln(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("ln(x) is undefined for x <= 0");
        }
        double result = 0;
        double term = (value - 1) / (value + 1);
        double termSquared = term * term;
        double denominator = 1;
        double currentTerm = term;

        for (int i = 0; i < 100; i++) {
            result += currentTerm;
            denominator += 2;
            currentTerm *= termSquared;
            currentTerm /= denominator;
        }
        return 2 * result;
    }

    private static double exp(double x) {
        double result = 1;
        double term = 1;
        for (int i = 1; i < 100; i++) {
            term *= x / i;
            result += term;
        }
        return result;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PowerCalculatorGUI().setVisible(true));
    }
}
