package com.tnative;

import com.io.Demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sean on 2018/5/30.
 */
public class TestNative {
//    public static native void timer();
//    static {
//        System.loadLibrary("TestNative");
//    }

    public static void main(String[] args) {
//        boolean b = (1 == 1) ? true : false;
        List<String> stringList = new ArrayList<>();
        stringList.add("sdsd");
        stringList.add("123ssdasd");
        stringList.add("sds");
//        List<String> sds = stringList.stream().filter(s -> s.equals("sds")).collect(Collectors.toList());
//        List<Integer> collect = stringList.stream().map(String::length).sorted().collect(Collectors.toList());
        List<Demo> demoList = new ArrayList<>();
        demoList.add(new Demo("sean", "123"));
        demoList.add(new Demo("master", "22"));
//        StringBuilder stringBuilder=new StringBuilder();
//        demoList.stream().forEach(demo ->stringBuilder.append(demo.getName()).append(":").append(demo.getAge()).append(","));
        demoList.stream().forEach(demo -> demo.setAge("222"));
        List<Integer> integerList = new ArrayList<>();
        integerList.add(12);
        integerList.add(23);
        integerList.add(23);
        integerList.add(23);
        Integer reduce = integerList.stream().reduce(0, (x, y) -> {
            return x + y;
        });
    }
}
