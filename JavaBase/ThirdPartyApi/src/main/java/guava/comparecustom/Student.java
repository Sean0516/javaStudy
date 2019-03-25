package guava.comparecustom;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Multimap;
import sun.dc.pr.PRError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sean on 2019/3/21
 *
 * @author Sean
 */
public class Student implements Comparable<Student> {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Student o) {
        return ComparisonChain.start().compare(age, o.getAge()).result();
    }
    public static void main(String[] args) {
        List<Student> studentList=new ArrayList<>();
        studentList.add(new Student("sean",22312));
        studentList.add(new Student("22",32));
        studentList.add(new Student("sdfa",223));
        List<Student> collect = studentList.stream().sorted().collect(Collectors.toList());
        System.out.println(collect);
    }
}
