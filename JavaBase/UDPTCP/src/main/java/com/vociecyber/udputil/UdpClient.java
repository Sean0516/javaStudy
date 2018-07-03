package com.vociecyber.udputil;

import com.alibaba.fastjson.JSONObject;
import com.vociecyber.udp.Demo1;

import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by Sean on 2018/7/3
 *
 * @author Sean
 */
public class UdpClient {
    /**
     * 设置接收数据的超时时间
     */
    private static final int TIME_OUT_TIME = 1000;
    /**
     * 设置重发数据的最多次数
     */
    private static final int MAX_SEND_NUM = 2;

    public static void udpClient(int client, String ipAddress, int server, Object object) throws Exception {
        byte[] buf = new byte[1024];
        //客户端 监听端口
        DatagramSocket datagramSocket = new DatagramSocket(client);
        InetAddress address = InetAddress.getByName(ipAddress);
        String str = JSONObject.toJSONString(object);
        //发送数据端口
        DatagramPacket sendMessage = new DatagramPacket(str.getBytes(), str.getBytes().length, address, server);
        //获取数据缓冲池
        DatagramPacket datagramPacketReceive = new DatagramPacket(buf, 1024);
        //设置接收数据时阻塞的最长时间
        datagramSocket.setSoTimeout(TIME_OUT_TIME);
        //重发次数
        int count = 0;
        boolean receivedResponse = false;
        while (!receivedResponse && count < MAX_SEND_NUM) {
            datagramSocket.send(sendMessage);
            try {
                datagramSocket.receive(datagramPacketReceive);
                receivedResponse = true;
            } catch (InterruptedIOException e) {
                count += 1;
                System.out.println("重新连接 次数："+count);
            }
        }
        if (receivedResponse) {
            System.out.println("数据发送成功");
        } else {
            //如果多次重复发送后，仍未获得服务器发送回来的数据
            System.out.println("服务器无响应");
        }
        datagramSocket.close();
    }
}
