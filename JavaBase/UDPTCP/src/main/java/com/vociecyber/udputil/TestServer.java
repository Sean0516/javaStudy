package com.vociecyber.udputil;

import java.io.IOException;

/**
 * Created by Sean on 2018/7/3
 *
 * @author Sean
 */
public class TestServer {
    public static void main(String[] args) throws IOException {
        UdpServer.udpServer(26000,9000);
    }
}
