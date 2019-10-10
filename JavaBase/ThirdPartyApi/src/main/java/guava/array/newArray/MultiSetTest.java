package guava.array.newArray;

import com.google.common.collect.*;
import com.sun.org.apache.bcel.internal.generic.NEW;
import common.moduel.User;
import guava.comparecustom.Student;

import java.util.Comparator;

/**
 * Created by Sean on 2019/3/22
 *
 * @author Sean
 */
public class MultiSetTest {
    public static void main(String[] args) {
//        HashMultiset<String> multiset = HashMultiset.create();
//        multiset.add("sean");
//        multiset.add("test", 3);
//        multiset.add("master");
////        给定元素在MultiSet的计数
//        System.out.println(multiset.count("sean"));
////        MultiSet中不重复元素的集合
//        System.out.println(multiset.elementSet().size());
////        设置给定元素在MultiSet 中的计数，不可以为负数
//        multiset.setCount("my", 3);
////        放回集合元素的总个数（包括重复元素
//        System.out.println(multiset.size());
//        TreeMultiset<User> multisetTree = TreeMultiset.create((Comparator<User>) (o1, o2) -> ComparisonChain.start().compare(o1.getAge(), o2.getAge()).result());
//        multisetTree.add(new User("sean",122,"334"),2);
//        multisetTree.add(new User("st",22,"男"));
//        multisetTree.add(new User("test",4,"女"),4);
//        System.out.println(multisetTree.count(new User("sean", 12, "334")));
//        System.out.println(multisetTree.size());
        ConcurrentHashMultiset concurrentHashMultiset=ConcurrentHashMultiset.create();
        User sean = new User("sean", 122, "334");
        User user = new User("st", 22, "男");
        User sean2 = new User("sean", 122, "334");
        User user1 = new User("test", 4, "女");
        concurrentHashMultiset.add(sean);
        concurrentHashMultiset.add(user);
        concurrentHashMultiset.add(user1);
        concurrentHashMultiset.add(sean2);
        System.out.println(concurrentHashMultiset.count(sean));


    }
}
