package com.keke.demo;

import com.keke.spring.Autowired;
import com.keke.spring.Component;

/**
 * @author k 2023/1/9 15:23
 */
@Component
public class UserService implements UserInferface {

    @Autowired
    private OrderService orderService;

    public UserService() {
        System.out.println("我是构造方法");
    }

    @Override
    public void test() {
        System.out.println(orderService);
    }

}
