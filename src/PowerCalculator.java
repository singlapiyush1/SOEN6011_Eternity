import java.util.Scanner;

public class PowerCalculator {

    // Method to compute x^y
    public static double power(double x, double y) {
        // Handle special cases
        if (x == 0 && y == 0) {
            throw new ArithmeticException("0^0 is undefined.");
        }
        if (x == 0) {
            return 0;
        }
        // Used Math.pow for all cases, including fractional exponents
        return Math.pow(x, y);
    }

    public static void main(String[] args) {
        // Create a Scanner object for reading input
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            try {
                // Display menu
                System.out.println("Menu:");
                System.out.println("1: Compute power");
                System.out.println("2: Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // Prompt the user to enter the base value (x)
                        System.out.print("Enter the base value (x): ");
                        double x = scanner.nextDouble();

                        // Prompt the user to enter the exponent value (y)
                        System.out.print("Enter the exponent value (y): ");
                        double y = scanner.nextDouble();

                        // Compute x^y using the power method
                        double result = power(x, y);

                        // Display the result
                        System.out.println(x + " raised to the power of " + y + " is: " + result);
                        break;

                    case 2:
                        // Exit the program
                        running = false;
                        System.out.println("Exiting the program. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid option. Please choose 1 or 2.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); // Clear the input buffer
            }
        }

        // Close the scanner
        scanner.close();
    }
}
