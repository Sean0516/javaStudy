package com.innerclass;

/**
 * Created by Sean on 2018/5/8.
 */
public class OuterClass {
//    内部类访问外部类对象状态
    private  String  str="timer";
//        局部内部类
     public void test(){
          String name="Sean";
         class PartInnerClass{
             private int age=10;
         }
     }
    public class InnerClass{
        private int num=12;
        public void test(){
            System.out.println(num+str);
        }
    }


}
