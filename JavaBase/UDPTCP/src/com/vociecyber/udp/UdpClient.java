package com.vociecyber.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * Created by Sean on 2018/6/1.
 */
public class UdpClient {
    public static void createUdpClient(int clientPort,String serviceIp,int serverPort) throws IOException {
//        客户端 socket 用于发送消息
        String clientMsg="";
        DatagramSocket client=new DatagramSocket(clientPort);
        client.setSoTimeout(5000);
        byte[] buf = new byte[1024];
        InetAddress serverAddress=InetAddress.getByName(serviceIp);
        BufferedReader clientInput=new BufferedReader(new InputStreamReader(System.in));
        DatagramPacket datagramPacketReceive = new DatagramPacket(buf, 1024);
        boolean flag=true;
        while (flag){
            System.out.println("输入发送的消息：");
            clientMsg=clientInput.readLine();
            DatagramPacket clientPacket=new DatagramPacket(clientMsg.getBytes(),clientMsg.getBytes().length,serverAddress,clientPort);


        }
    }
}
