package jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author sean
 * @date 2019/12/17  15:23
 */
@XmlRootElement(name = "StudentInfo")
@XmlType(propOrder = {"id","name","age"})
public class Student {
    private String teacher;
    private String id;
    private String name;
    private Integer age;


    public Student() {
    }

    public Student(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Student(String teacher, String id, String name, Integer age) {
        this.teacher = teacher;
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getTeacher() {
        return teacher;
    }
    @XmlAttribute(name = "老师")
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    @XmlElement(name = "studentName" ,defaultValue = "Sean" ,required = true)
    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "Student{" +
                "teacher='" + teacher + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
