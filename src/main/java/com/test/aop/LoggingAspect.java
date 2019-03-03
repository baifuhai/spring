package com.test.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * AOP 的 helloWorld
 * 1. 加入 jar 包
 * com.springsource.net.sf.cglib-2.2.0.jar
 * com.springsource.org.aopalliance-1.0.0.jar
 * com.springsource.org.aspectj.weaver-1.6.8.RELEASE.jar
 * spring-aspects-4.0.0.RELEASE.jar
 *
 * 2. 在 Spring 的配置文件中加入 aop 的命名空间。
 *
 * 3. 基于注解的方式来使用 AOP
 * 3.1 在配置文件中配置自动扫描的包: <context:component-scan base-package="com.atguigu.spring.aop"></context:component-scan>
 * 3.2 加入使 AspjectJ 注解起作用的配置: <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
 * 为匹配的类自动生成动态代理对象.
 *
 * 4. 编写切面类:
 * 4.1 一个一般的 Java 类
 * 4.2 在其中添加要额外实现的功能.
 *
 * 5. 配置切面
 * 5.1 切面必须是 IOC 中的 bean: 实际添加了 @Component 注解
 * 5.2 声明是一个切面: 添加 @Aspect
 * 5.3 声明通知: 即额外加入功能对应的方法.
 * 5.3.1 前置通知: @Before("execution(public int com.atguigu.spring.aop.ArithmeticCalculator.*(int, int))")
 * @Before 表示在目标方法执行之前执行 @Before 标记的方法的方法体.
 * @Before 里面的是切入点表达式:
 *
 * 6. 在通知中访问连接细节: 可以在通知方法中添加 JoinPoint 类型的参数, 从中可以访问到方法的签名和方法的参数.
 *
 * 7. @After 表示后置通知: 在方法执行之后执行的代码.
 */

// 可以使用 @Order 注解指定切面的优先级，值越小优先级越高，越先执行
@Order(1)
// 通过添加 @Aspect 注解把这个类声明为一个切面，还要放到IOC容器中
@Aspect
@Component
public class LoggingAspect {

    /**
     * 定义一个方法，用于声明切入点表达式，一般地，该方法中再不需要添入其他的代码
     * 使用 @Pointcut 来声明切入点表达式
     * 后面的其他通知直接使用方法名来引用当前的切入点表达式
     */
    @Pointcut("execution(* com.test.aop.Calculator.*(..)) || execution(* com.test.aop.Calculator.*(..))")
    public void pointCut(){}

    // 前置通知，在目标方法执行之前执行
    //@Before("execution(* com.test.aop.Calculator.*(..))")
    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        System.out.println("before: " + methodName + ", args: " + Arrays.asList(args));
    }

    // 后置通知，在目标方法执行之后执行，无论是否发生异常，不能访问方法返回值
    @After("pointCut()")
    public void after(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();

        System.out.println("after: " + methodName);
    }

    // 返回通知，在目标方法正常返回后执行，能访问方法返回值
    @AfterReturning(value = "pointCut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();

        System.out.println("after returning: " + methodName + ", result: " + result);
    }

    // 异常通知，在目标方法抛出异常后执行，能访问异常对象，且可以指定在出现特定异常时在执行通知代码
    @AfterThrowing(value = "pointCut()", throwing = "throwable")
    public void afterThrowing(JoinPoint joinPoint, ArithmeticException throwable) {
        String methodName = joinPoint.getSignature().getName();

        System.out.println("after throwing: " + methodName + ", throwable: " + throwable);
    }

    /**
     * 环绕通知需要携带 ProceedingJoinPoint 类型的参数
     * 环绕通知类似于动态代理的全过程: ProceedingJoinPoint 类型的参数可以决定是否执行目标方法
     * 且环绕通知必须有返回值，返回值即为目标方法的返回值
     */
	//@Around("pointCut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();

		Object result = null;
        Throwable throwable = null;

		try {
			// 前置通知
			System.out.println("before: " + methodName + ", args: " + Arrays.asList(args));

			// 执行目标方法
			result = pjp.proceed();
		} catch (Throwable e) {
            throwable = e;
		}

		// 后置通知
		System.out.println("after: " + methodName);

		if (throwable != null) {
            // 异常通知
            System.out.println("after throwing: " + methodName + ", throwable: " + throwable);
            throw throwable;
        }

        // 返回通知
        System.out.println("after returning: " + methodName + ", result: " + result);

		return result;
	}

}
