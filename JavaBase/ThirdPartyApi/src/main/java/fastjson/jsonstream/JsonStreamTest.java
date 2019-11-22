package fastjson.jsonstream;

import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.JSONWriter;
import common.moduel.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sean on 2019/3/13
 *
 * @author Sean
 */
public class JsonStreamTest {
    public static void main(String[] args) throws IOException {
//        JSONWriter writer=new JSONWriter(new FileWriter("D:\\streamTest.json"));
//        writer.startArray();
//        for (int i = 0; i < 1000; i++) {
//            User user=new User("timer",i,"男");
//            writer.writeValue(user);
//        }
//        writer.endArray();
//        writer.close();
        List<User> userList = new ArrayList<>();
        JSONReader reader = new JSONReader(new FileReader("D:\\\\streamTest.json"));
        reader.startArray();
        while (reader.hasNext()) {
           userList.add( reader.readObject(User.class));
        }
        reader.endArray();
        reader.close();
        System.out.println(userList.size());

    }
}
