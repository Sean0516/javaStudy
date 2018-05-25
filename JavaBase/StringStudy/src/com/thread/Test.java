package com.thread;


import java.util.concurrent.*;

/**
 * Created by Sean on 2018/5/24.
 */
public class Test {
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newSingleThreadScheduledExecutor();
        ScheduledExecutorService scheduledExecutorService=Executors.newScheduledThreadPool(3);
       new  Thread(new Runnable() {
           @Override
           public void run() {
            for (int i=0;i<5;i++){
                TestReadAndWriteLock.readFile(Thread.currentThread());
            }
           }
       }).start();
        new  Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<5;i++){
                    TestReadAndWriteLock.writeFile(Thread.currentThread());
                }
            }
        }).start();

    }
}
