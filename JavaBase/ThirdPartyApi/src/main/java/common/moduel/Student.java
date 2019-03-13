package common.moduel;

import com.alibaba.fastjson.annotation.JSONField;
import fastjson.jsonfield.AgeValueSerializer;

import java.util.Date;

/**
 * Created by Sean on 2019/3/13
 *
 * @author Sean
 */
public class Student {
    @JSONField(name = "UserName",ordinal = 1)
    private String name;
    @JSONField(name = "Birthday",ordinal = 3,format = "yyyy-MM-dd")
    private Date bir;
    @JSONField(name = "性别" ,ordinal = 2)
    private String sex;
    @JSONField(serializeUsing = AgeValueSerializer.class ,name = "年龄",ordinal = 4)
    private int age;

    @JSONField(serialize = false)
    private String des;

    public Student(String name, Date bir, String sex, int age, String des) {
        this.name = name;
        this.bir = bir;
        this.sex = sex;
        this.age = age;
        this.des = des;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBir() {
        return bir;
    }

    public void setBir(Date bir) {
        this.bir = bir;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
