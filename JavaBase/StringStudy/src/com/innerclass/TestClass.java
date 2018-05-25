package com.innerclass;

/**
 * Created by Sean on 2018/5/9.
 */
public class TestClass {
    public static void main(String[] args) {
        int[] arr=new int[20];
        for (int i =0;i<arr.length;i++){
            arr[i]=(int) Math.ceil(Math.random()*1000);
        }
        StaticInnerClass.Pair pair= StaticInnerClass.Pair.minmax(arr);
        System.out.println("Min"+pair.getMin() +"Max"+pair.getMax());

    }
}
