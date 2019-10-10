package com.vociecyber.tcp.bio;

import java.io.*;
import java.net.Socket;

/**
 * Created by Sean on 2018/6/1.
 */
public class TcpClient {
    public static void createTcpClient(String url,int port) throws IOException {
        String serverMsg="";
        Socket clientSocket=new Socket(url,port);
        clientSocket.setSoTimeout(4000);
        BufferedReader userInput=new BufferedReader(new InputStreamReader(System.in));
        BufferedReader serverInput=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintStream clientOut=new PrintStream(clientSocket.getOutputStream());
        boolean flag=true;
        System.out.println("开启客户端");
        while (flag){
            System.out.println("输入需要发送的消息");
            String  msg=userInput.readLine();
            if (msg.equals("")&&msg==null){
                continue;
            }
            clientOut.println(msg);
            serverMsg=serverInput.readLine();
            System.out.println("服务器回复消息:"+serverMsg);
            if (msg.equals("bye")){
                flag=false;
            }
        }
        if (clientSocket!=null){
            clientSocket.close();
            System.out.println("客户端关闭");
        }
    }

    public static void main(String[] args) throws IOException {
        TcpClient.createTcpClient("127.0.0.1",8888);
    }
}
