package com.vociecyber.udp;

/**
 * Created by Sean on 2018/7/3
 *
 * @author Sean
 */
public class TestClient {
    public static void main(String[] args) throws Exception {
        Demo1 demo1 = Client.udpClient(9000, "192.168.6.80", 26000, "你好 客户端");
        System.out.println(demo1.toString());
    }
}
