package com.vociecyber.udputil;

import com.alibaba.fastjson.JSONObject;
import com.vociecyber.udp.Demo1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by Sean on 2018/7/3
 *
 * @author Sean
 */
public class UdpServer {
    public static void udpServer(int serverPort, int clientPort) throws IOException {
        String str="hello client";
        byte[] buf = new byte[1024];
        boolean flag = true;
        //服务端口
        DatagramSocket datagramSocket = new DatagramSocket(serverPort);
        DatagramPacket dataReceive = new DatagramPacket(buf, 1024);
        System.out.println("服务器开启，等待客户端访问");
        while (flag) {
            datagramSocket.receive(dataReceive);
            String receiveMessage=new String(dataReceive.getData(),"UTF-8");
            if (receiveMessage!=null&&receiveMessage.trim().length()>0){
                JSONObject jsonObject = JSONObject.parseObject(receiveMessage);
                if (jsonObject!=null){
                    Demo1 demoReceive = JSONObject.toJavaObject(jsonObject, Demo1.class);
                    System.out.println(demoReceive.toString());
                }
            }
            DatagramPacket data_send = new DatagramPacket(str.getBytes(), str.getBytes().length, dataReceive.getAddress(), clientPort);
            datagramSocket.send(data_send);
            //发送数据到客户端端口上
            dataReceive.setLength(1024);
        }
        datagramSocket.close();
    }
}
