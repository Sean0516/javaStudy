package com.vociecyber.udp;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by Sean on 2018/7/3
 *
 * @author Sean
 */
public class Client {
    /**
     * 设置接收数据的超时时间
     */
    private static final int TIME_OUT_TIME = 5000;
    /**
     * 设置重发数据的最多次数
     */
    private static final int MAX_SEND_NUM = 5;

    /**
     * @param client    客户端端口
     * @param ipAddress 服务端ip地址
     * @param server    服务端端口
     * @param str       发生的数据
     * @return
     * @throws Exception
     */
    public static Demo1 udpClient(int client, String ipAddress, int server, String str) throws Exception {
        Demo1 demo1=new Demo1("master","123");
        System.out.println(demo1);
        byte[] buf = new byte[1024];
        //客户端 监听端口
        DatagramSocket datagramSocket = new DatagramSocket(client);
        InetAddress address = InetAddress.getByName(ipAddress);
        //发送数据端口
        DatagramPacket datagramPacketSend = new DatagramPacket(str.getBytes(), str.getBytes().length, address, server);
        //获取数据缓冲池
        DatagramPacket datagramPacketReceive = new DatagramPacket(buf, 1024);
        //设置接收数据时阻塞的最长时间
        datagramSocket.setSoTimeout(TIME_OUT_TIME);
        //重发次数
        int count = 0;
        boolean receivedResponse = false;

        while (!receivedResponse && count < MAX_SEND_NUM) {
            //发送数据
            datagramSocket.send(datagramPacketSend);
            try {
                //接收从服务端发送回来的数据
                datagramSocket.receive(datagramPacketReceive);
                //如果接收到数据。则将receivedResponse标志位改为true
                receivedResponse = true;
            } catch (InterruptedIOException e) {
                //如果接收数据时阻塞超时，重发并减少一次重发的次数
                count += 1;
            }
        }
        if (receivedResponse) {
            byte[] data = datagramPacketReceive.getData();
            String str1 = new String(data ,"UTF-8");
            if (str1!=null&&str1.trim().length()>0){
                JSONObject jsonObject = JSONObject.parseObject(str1);
                if (jsonObject!=null){
                    Demo1 demoReceive = JSONObject.toJavaObject(jsonObject, Demo1.class);
                    demo1=demoReceive;
                }
            }

            //由于在接收了数据之后，其内部消息长度值会变为实际接收的消息的字节数， 所以这里要将
            // datagramPacketReceive的内部消息长度重新置为1024
            datagramPacketReceive.setLength(1024);
        } else {
            //如果多次重复发送后，仍未获得服务器发送回来的数据
            System.out.println("服务器无响应");
        }
        datagramSocket.close();
        return demo1;
    }
}
