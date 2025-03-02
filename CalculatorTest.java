package calculator;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {
	
	
	Calculator calc = new Calculator();
	   
    @Test
    public void testAddPositiveIntegers()
    {
        assertEquals(19, calc.add(9, 10));
    }
    
    @Test
    public void testAddNegativeIntegers()
    {
        assertEquals(1, calc.add(-9, 10));
    }

    @Test
    public void testAdd() {
        assertEquals(25, Calculator.add(12, 13));
        assertEquals(0, Calculator.add(0, 0));
        assertEquals(-5, Calculator.add(-2, -3));
        assertEquals(5, Calculator.add(8, -3));
    }

    @Test
    public void testSubtract() {
        assertEquals(-1, Calculator.subtract(12, 13));
        assertEquals(0, Calculator.subtract(0, 0));
        assertEquals(1, Calculator.subtract(-2, -3));
        assertEquals(11, Calculator.subtract(8, -3));
    }

    @Test
    public void testMultiply() {
        assertEquals(156, Calculator.multiply(12, 13));
        assertEquals(0, Calculator.multiply(0, 10));
        assertEquals(6, Calculator.multiply(-2, -3));
        assertEquals(-24, Calculator.multiply(8, -3));
    }

    @Test
    public void testDivide() {
        assertEquals(0, Calculator.divide(12, 13)); // Integer division
        assertEquals(0, Calculator.divide(0, 10));
        assertEquals(1, Calculator.divide(-3, -3));
        assertEquals(-2, Calculator.divide(8, -3)); // Integer division
        assertEquals(2, Calculator.divide(6, 3));
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZero() {
        Calculator.divide(10, 0);
    }
}