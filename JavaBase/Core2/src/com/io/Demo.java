package com.io;

import java.io.Serializable;

/**
 * Created by Sean on 2018/5/25.
 */
public class Demo implements Serializable{
    private String name;
    private String age;
    public Demo(String name, String age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
