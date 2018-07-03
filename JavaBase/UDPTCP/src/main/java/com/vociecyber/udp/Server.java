package com.vociecyber.udp;

import com.alibaba.fastjson.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by Sean on 2018/7/3
 *
 * @author Sean
 */
public class Server {
    public static void udpServer(int serverPort, int clientPort,Object objects) throws IOException {
        byte[] buf = new byte[1024];
        boolean flag = true;
        String string = JSONObject.toJSONString(objects);
        //服务端口
        DatagramSocket datagramSocket = new DatagramSocket(serverPort);
        DatagramPacket dataReceive = new DatagramPacket(buf, 1024);
        System.out.println("服务器开启，等待客户端访问");
        while (flag) {
            datagramSocket.receive(dataReceive);
            String receiveMessage = new String(dataReceive.getData(), 0, dataReceive.getLength()) +
                    " 来自ip " + dataReceive.getAddress().getHostAddress() + "端口:" + dataReceive.getPort();
            System.out.println(receiveMessage);
            //发送数据到客户端端口上
            DatagramPacket data_send = new DatagramPacket(string.getBytes(), string.getBytes().length, dataReceive.getAddress(), clientPort);
            datagramSocket.send(data_send);
            dataReceive.setLength(1024);
        }
        datagramSocket.close();

    }
}
