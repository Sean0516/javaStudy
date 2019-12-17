package jaxb;

import javax.xml.bind.JAXB;
import java.io.File;

/**
 * @author sean
 * @date 2019/12/17  15:24
 */
public class Test {
    public static void main(String[] args) {
        Student student=new Student("demo","0212","sean",22);
        JAXB.marshal(student,new File("D:\\student.xml"));
        Student unmarshal = JAXB.unmarshal(new File("D:\\student.xml"), Student.class);
        System.out.println(unmarshal.toString());
    }

}
