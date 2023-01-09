package com.keke.demo;

import com.keke.spring.BeanPostProcessor;
import com.keke.spring.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author k 2023/1/9 19:47
 */
@Component
public class KBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessorBeforeInitialization(String beanName, Object bean) {
        if (beanName.equals("userService")) {
            System.out.println("111111111111");
        }
        return bean;
    }

    @Override
    public Object postProcessorAfterInitialization(String beanName, Object bean) {
        if (beanName.equals("userService")) {
            Object instance = Proxy.newProxyInstance(KBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("切面逻辑");
                    return method.invoke(bean, args);
                }
            });
            return instance;
        }

        return bean;
    }
}
