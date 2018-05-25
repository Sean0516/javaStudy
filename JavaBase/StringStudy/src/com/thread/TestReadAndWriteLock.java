package com.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Sean on 2018/5/24.
 */
public class TestReadAndWriteLock {
    private static ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();
    private static Lock readLock=reentrantReadWriteLock.readLock();
    private static Lock writeLock=reentrantReadWriteLock.writeLock();
    public static void readFile(Thread thread){
        readLock.lock();
        System.out.println("开始读文件");
        System.out.println("当前线程" +thread.getName());
        readLock.unlock();
    }
    public static void writeFile(Thread thread){
        writeLock.lock();
        System.out.println("开始写文件");
        System.out.println("当前线程" +thread.getName());
        writeLock.unlock();
    }
}
