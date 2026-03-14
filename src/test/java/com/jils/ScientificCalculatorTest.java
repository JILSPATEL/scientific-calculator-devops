package com.jils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ScientificCalculatorTest {

    ScientificCalculator calc = new ScientificCalculator();

    @Test
    void testSquareRoot() {
        assertEquals(5.0, calc.squareRoot(25));
    }

    @Test
    void testFactorial() {
        assertEquals(120, calc.factorial(5));
    }

    @Test
    void testNaturalLog() {
        assertEquals(1.0, calc.naturalLog(Math.E), 0.0001);
    }

    @Test
    void testPower() {
        assertEquals(8.0, calc.power(2,3));
    }

    @Test
    void testCosine(){
        assertEquals(1.0,calc.cosine(0),0.0001);
    }
}