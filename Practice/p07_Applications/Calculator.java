package p07_Applications;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\n[Enter expression (e.g., 2+3) or 'exit']\n> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit"))
                break;

            try {
                double result = evaluate(input);
                System.out.println("Result: " + result);
            } catch (Exception e) {
                System.out.println("Error: Invalid expression.");
            }
        }
        scanner.close();
    }

    private static double evaluate(String expression) {
        expression = expression.replaceAll("\\s+", "");

        char operator = ' ';
        int opIndex = -1;

        // Find operator (skipping index 0 for negative numbers)
        for (int i = 1; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                operator = c;
                opIndex = i;
                break;
            }
        }

        if (opIndex == -1)
            throw new IllegalArgumentException();

        double num1 = Double.parseDouble(expression.substring(0, opIndex));
        double num2 = Double.parseDouble(expression.substring(opIndex + 1));

        return switch (operator) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> num1 / num2;
            default -> throw new IllegalArgumentException();
        };
    }
}