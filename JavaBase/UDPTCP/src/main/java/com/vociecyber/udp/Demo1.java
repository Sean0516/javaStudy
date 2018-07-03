package com.vociecyber.udp;

import java.io.Serializable;

/**
 * Created by Sean on 2018/7/3
 *
 * @author Sean
 */
public class Demo1 {

    private String name;
    private String sex;
    public Demo1(String name, String sex){
        this.name=name;
        this.sex=sex;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Demo1{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
