package fastjson.jsonfield;

import com.alibaba.fastjson.JSON;
import common.moduel.Student;

import java.util.Date;

/**
 * Created by Sean on 2019/3/13
 *
 * @author Sean
 */
public class JSONFieldTest {
    public static void main(String[] args) {
        Student student=new Student("Sean",new Date(),"男",22,"timer");
        String s = JSON.toJSONString(student);
        System.out.println(s);

    }
}
