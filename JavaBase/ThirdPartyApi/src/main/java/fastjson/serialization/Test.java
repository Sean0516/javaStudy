package fastjson.serialization;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import common.moduel.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by Sean on 2019/3/12
 *
 * @author Sean
 */
public class Test {
    public static void main(String[] args) throws IOException {
        User user = new User("sean", 22, "男");
        String s = JSON.toJSONString(user);
        System.out.println(s);
        byte[] bytes = JSON.toJSONBytes(user);
//        FileOutputStream fileOutputStream=new FileOutputStream("D:\\test.json");
//        JSON.writeJSONString( fileOutputStream,user);
//        User user1 = JSON.parseObject(s, User.class);
//        User user2 = JSON.parseObject(bytes, User.class);
//        System.out.println(user2);
//        JSONObject jsonObject = JSON.parseObject(s);
        User user3 = JSON.parseObject(new FileInputStream("D:\\test.json"), Charset.defaultCharset(), User.class);
        System.out.println(user3);

    }
}
