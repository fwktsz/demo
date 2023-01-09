package com.keke.entity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author k 2022/12/28 16:47
 */
public class RFdemo {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class clz = Class.forName("com.keke.entity.Person");
        Constructor declaredConstructor = clz.getDeclaredConstructor();
        Person o = (Person)declaredConstructor.newInstance();
        System.out.println(o);
    }

}
