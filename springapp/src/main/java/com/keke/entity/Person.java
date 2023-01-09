package com.keke.entity;

/**
 * @author k 2022/12/28 14:07
 */
public class Person {

    public Person(String name) {
        this.name = name;
    }

    public Person() {
        System.out.println("Person init");
    }

    private String name;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}

