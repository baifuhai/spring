package com.test.aop;

public class CalculatorLoggingImpl extends CalculatorImpl {

    @Override
    public int add(int a, int b) {
        System.out.println("before add...");
        int result = super.add(a, b);
        System.out.println("after add...");
        return result;
    }

    @Override
    public double div(int a, int b) {
        System.out.println("before div...");
        double result = super.div(a, b);
        System.out.println("after div...");
        return result;
    }

}
