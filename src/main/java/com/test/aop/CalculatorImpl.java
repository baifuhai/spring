package com.test.aop;

import org.springframework.stereotype.Component;

@Component
public class CalculatorImpl implements Calculator {

    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public double div(int a, int b) {
        return (double) a / b;
    }

}
