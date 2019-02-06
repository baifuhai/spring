package com.test.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class CalculatorTimingProxy {

    private static class MyInvocationHandler implements InvocationHandler {

        private Calculator target;

        public MyInvocationHandler(Calculator target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("timing, before: " + method.getName() + ", args: " + Arrays.asList(args));

            long begin = System.currentTimeMillis();

            Object result = method.invoke(target, args);

            long end = System.currentTimeMillis();

            System.out.println("timing, after: " + method.getName() + ", time: " + (end - begin) + " ms");

            return result;
        }

    }

    public static Calculator getProxy(Calculator target) {
        ClassLoader classLoader = target.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        InvocationHandler invocationHandler = new MyInvocationHandler(target);
        return (Calculator) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
    }

}
