package com.voicecyber;

import java.io.File;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

/**
 * Created by Sean on 2018/6/4.
 */
public class TestLambda {
    public static void main(String[] args) {
//         接受一个对象，返回一个布尔值
        Predicate predicate=(a)-> (int)a!=1;
//        二元运算    该接口接受两个参数， 返回一个值 ，参数和值的类型均相同
        BinaryOperator<Long> addLong=(x,y)->x+y;
        List<Long> list=new ArrayList<>();
        list.add(1L);
        list.add(22L);
        list.add(3321l);
//        list.stream().filter((x)->x>23L).count();
//        list.stream().reduce(addLong);
        Map<Boolean, List<Long>> collect = list.stream().collect(Collectors.partitioningBy(x -> x > 23L));

//        List<Integer> collect = Stream.of(12, 3321, 313, 123).sorted(Integer::compareTo).collect(Collectors.toList());
//        list.stream().min(Comparator.comparing(l->Long.BYTES));
    }
}
