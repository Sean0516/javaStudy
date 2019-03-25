package common.moduel;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Sean on 2019/3/12
 *
 * @author Sean
 */
public class User {
    private String name;
    private int age;
    private String sex;

    public User(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "{" +
                "name:'" + name + '\'' +
                ", age:" + age +
                ", sex:'" + sex + '\'' +
                '}';
    }
}
