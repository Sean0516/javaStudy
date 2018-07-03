package com.vociecyber.udputil;

import com.vociecyber.udp.Demo1;

/**
 * Created by Sean on 2018/7/3
 *
 * @author Sean
 */
public class TestClient {
    public static void main(String[] args) throws Exception {
        UdpClient.udpClient(9000,"192.168.6.80",26000,new Demo1("sean","nan"));
    }
}
