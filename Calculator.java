package calculator;

public class Calculator {

      public static long add(int numOne, int numTwo) {
        int result = numOne + numTwo;
        System.out.println(numOne + " + " + numTwo + " = " + result);
        return result;
      }

      public static long subtract(int numOne, int numTwo) {
        int result = numOne - numTwo;
        System.out.println(numOne + " - " + numTwo + " = " + result);
        return result;
      }

      public static long multiply(int numOne, int numTwo) {
        int result = numOne * numTwo;
        System.out.println(numOne + " * " + numTwo + " = " + result);
        return result;
      }

      public static long divide(int numOne, int numTwo) {
        int result = numOne / numTwo;
        System.out.println(numOne + " / " + numTwo + " = " + result);
        return result;
      }

      public static void main(String[] args) {
        int numOne = 12;
        int numTwo = 13;
        add(numOne, numTwo);
        subtract(numOne, numTwo);
        multiply(numOne, numTwo);
        divide(numOne, numTwo);
      }
}