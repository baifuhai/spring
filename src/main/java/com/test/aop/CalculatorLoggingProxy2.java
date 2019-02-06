package com.test.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class CalculatorLoggingProxy2 {

    private static class MyInvocationHandler implements InvocationHandler {

        private Calculator target;

        public MyInvocationHandler(Calculator target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("before: " + method.getName() + ", args: " + Arrays.asList(args));

            Object result = method.invoke(target, args);

            System.out.println("after: " + method.getName());

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
