package com.exception;

/**
 * Created by Sean on 2018/5/9.
 */
public class TestCustomException {
    public void test(){
        int i=2;
        if (i!=0){
            throw new  CustomException("输入错误，请重新输入",-1);
        }else {
            System.out.println("success");
        }
    }
    public static void main(String[] args) {
        TestCustomException testCustomException=new TestCustomException();
        try {
            testCustomException.test();
        } catch (CustomException e) {
            System.out.println(e.getCode()+e.getMessage());
        }
    }
}
