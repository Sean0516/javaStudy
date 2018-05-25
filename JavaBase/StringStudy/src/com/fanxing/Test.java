package com.fanxing;

/**
 * Created by Sean on 2018/5/21.
 */
public class Test {
    public static void main(String[] args) {
        Result result=new Result();
    }
    public static <T> T getData(T... result){
        return result[result.length];
    }
}
