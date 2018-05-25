package com.jihe;

import java.util.*;

/**
 * Created by Sean on 2018/5/24.
 */
public class TestQueu {
    public static void main(String[] args) {
        Queue<String> stringQueue=new ArrayDeque<>();
        stringQueue.add("123");
        stringQueue.remove();
        System.out.println(stringQueue.peek());
        Queue<String> queue=new LinkedList<>();
        List<String> list=new LinkedList<>();
        Iterator<String> stringIterator=list.iterator();
        while (stringIterator.hasNext()){
            System.out.println(stringIterator.next());
        }
        Queue queue1=new PriorityQueue();
    }
}
