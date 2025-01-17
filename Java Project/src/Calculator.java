import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Basic Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 1));


        JTextField firstNumberField = new JTextField();
        JTextField secondNumberField = new JTextField();


        String[] operations = {"Add", "Subtract", "Multiply", "Divide"};
        JComboBox<String> operationBox = new JComboBox<>(operations);


        JButton calculateButton = new JButton("Calculate");


        JLabel resultLabel = new JLabel("Result: ", SwingConstants.CENTER);


        frame.add(new JLabel("Enter first number:"));
        frame.add(firstNumberField);
        frame.add(new JLabel("Enter second number:"));
        frame.add(secondNumberField);
        frame.add(operationBox);
        frame.add(calculateButton);
        frame.add(resultLabel);


        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double num1 = Double.parseDouble(firstNumberField.getText());
                    double num2 = Double.parseDouble(secondNumberField.getText());
                    String operation = (String) operationBox.getSelectedItem();

                    double result;

                    switch (operation) {
                        case "Add":
                            result = num1 + num2;
                            break;
                        case "Subtract":
                            result = num1 - num2;
                            break;
                        case "Multiply":
                            result = num1 * num2;
                            break;
                        case "Divide":
                            if (num2 == 0) {
                                throw new ArithmeticException("Division by zero");
                            }
                            result = num1 / num2;
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + operation);
                    }

                    resultLabel.setText("Result: " + result);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid input. Please enter numbers.");
                } catch (ArithmeticException ex) {
                    resultLabel.setText(ex.getMessage());
                }
            }
        });


        frame.setVisible(true);
    }
}


