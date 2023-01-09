package com.keke.spring;

/**
 * @author k 2023/1/9 19:45
 */
public interface BeanPostProcessor {

    Object postProcessorBeforeInitialization(String beanName,Object bean);
    Object postProcessorAfterInitialization(String beanName,Object bean);
}
