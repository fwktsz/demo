package com.keke.demo;

import com.keke.spring.KApplicationContext;

/**
 * @author k 2023/1/9 15:21
 */
public class Test {
    public static void main(String[] args) {
        // ApplicationContext
        KApplicationContext kApplicationContext = new KApplicationContext(AppConfig.class);
        UserInferface userService = (UserInferface) kApplicationContext.getBean("userService");
        userService.test();

    }
}
