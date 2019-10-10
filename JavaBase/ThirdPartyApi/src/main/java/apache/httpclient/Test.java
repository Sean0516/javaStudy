package apache.httpclient;

import java.io.IOException;

/**
 * Created by Sean on 2019/3/25
 *
 * @author Sean
 */
public class Test {
    public static void main(String[] args) {
        HttpClientUtil util = HttpClientUtil.getInstance();
        try {
//            String s = util.httpClientGet("http://192.168.6.54:8080/test2/user");
//            String sean = util.httpClientPostJson("http://192.168.6.54:8080/test2/jsonTest", "test");
            String test = util.httpClientPostForm("http://192.168.6.54:8080/test2/tenancy", "test");
            System.out.println(test);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
