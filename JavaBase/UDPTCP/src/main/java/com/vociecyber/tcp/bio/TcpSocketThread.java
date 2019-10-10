package com.vociecyber.tcp.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.time.LocalDateTime;

/**
 * Created by Sean on 2018/6/1.
 */
public class TcpSocketThread implements Runnable {
    private Socket clientSocket;

    public TcpSocketThread(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            PrintStream printStream = new PrintStream(clientSocket.getOutputStream());
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            boolean flag=true;
            while (flag){
                String  clientMsg=bufferedReader.readLine();
                if (clientMsg==null&& clientMsg.equals("")){
                    flag=false;
                }else {
                    System.out.println("客服端"+clientSocket.getRemoteSocketAddress()+"传来消息"+clientMsg);
                    printStream.println("客户端，你好"+ LocalDateTime.now());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


