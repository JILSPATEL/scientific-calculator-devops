package com.jils;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ScientificCalculator calc = new ScientificCalculator();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Scientific Calculator ===");
            System.out.println("1. Square Root");
            System.out.println("2. Factorial");
            System.out.println("3. Natural Log");
            System.out.println("4. Power");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("No input detected. Running in background mode.");
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    break;
                }
                continue;
            }

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter number: ");
                    double num = sc.nextDouble();
                    System.out.println("Result: " + calc.squareRoot(num));
                    break;

                case 2:
                    System.out.print("Enter number: ");
                    int fact = sc.nextInt();
                    System.out.println("Result: " + calc.factorial(fact));
                    break;

                case 3:
                    System.out.print("Enter number: ");
                    double log = sc.nextDouble();
                    System.out.println("Result: " + calc.naturalLog(log));
                    break;

                case 4:
                    System.out.print("Enter base: ");
                    double base = sc.nextDouble();
                    System.out.print("Enter exponent: ");
                    double exp = sc.nextDouble();
                    System.out.println("Result: " + calc.power(base, exp));
                    break;

                case 5:
                    System.out.println("Exiting....");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}