package com.jils;

public class ScientificCalculator {

    public double squareRoot(double x) {
        return Math.sqrt(x);
    }

    public long factorial(int x) {
        if (x < 0)
            throw new IllegalArgumentException("Negative numbers not allowed");

        long result = 1;
        for (int i = 1; i <= x; i++) {
            result *= i;
        }
        return result;
    }

    public double naturalLog(double x) {
        if (x <= 0)
            throw new IllegalArgumentException("Log undefined for non-positive numbers");

        return Math.log(x);
    }

    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

}
