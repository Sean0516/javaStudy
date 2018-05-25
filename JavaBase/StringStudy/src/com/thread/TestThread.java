package com.thread;

/**
 * Created by Sean on 2018/5/24.
 */
public class TestThread implements Runnable {
    @Override
    public void run() {
        int i=0;
        for (int j=0;j<100;j++){
            i+=1;
            System.out.println("这是一个线程" +Thread.currentThread().getName() +i);
            if (i>4){
                break;
            }
        }

    }
}
