package com.test.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class CalculatorLoggingProxy {

    // 要代理的对象
    private Calculator target;

    public CalculatorLoggingProxy(Calculator target) {
        this.target = target;
    }

    // 返回代理对象
    public Calculator getProxy() {
        // 代理对象由哪个类加载器加载
        ClassLoader classLoader = target.getClass().getClassLoader();

        // 代理对象的接口类型，即代理对象中可以有哪些方法
        Class<?>[] interfaces = target.getClass().getInterfaces();
        //Class<?>[] interfaces = new Class<?>[]{Calculator.class};

        // 当调用代理对象的方法时，应该如何进行响应，实际上就是调用 InvocationHandler 的 invoke 方法
        InvocationHandler invocationHandler = new InvocationHandler() {
            /**
             * proxy: 代理对象，一般不使用该对象
             * method: 被调用的方法
             * args: 调用方法传入的参数
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String methodName = method.getName();

                Object result = null;
                Throwable throwable = null;

                try {
                    // 前置通知
                    System.out.println("before: " + methodName + ", args: " + Arrays.asList(args));

                    // 执行目标方法
                    result = method.invoke(target, args);
                } catch (Throwable e) {
                    throwable = e;
                }

                // 后置通知，因为方法可以能会出异常，所以访问不到方法的返回值
                System.out.println("after: " + methodName);

                if (throwable != null) {
                    // 异常通知，可以访问到方法出现的异常
                    System.out.println("after throwing: " + methodName + ", throwable: " + throwable);
                    throw throwable;
                }

                // 返回通知，可以访问到方法的返回值
                System.out.println("after returning: " + methodName + ", result: " + result);

                return result;
            }
        };

        Calculator proxy = (Calculator) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);

        return proxy;
    }

}
