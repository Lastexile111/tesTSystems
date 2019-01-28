package ru.tsystems.calculator;

public class Launcher {
    public static void main(String[] args) {
        Calculator c = new CalculatorImpl();
        System.out.println("1-----"+c.evaluate("(1+38)*4-5"));
        System.out.println("2-----"+c.evaluate("7*6/2+8"));
        System.out.println("3-----"+c.evaluate("-12)1//("));
        System.out.println("4-----"+c.evaluate("12)1/4"));
        System.out.println("5-----"+c.evaluate("5121++5"));
        System.out.println("6-----"+c.evaluate("435.12345"));
    }
}
