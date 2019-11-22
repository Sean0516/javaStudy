package com.vociecyber.test;

import com.vociecyber.udp.Demo1;

import javax.xml.ws.Endpoint;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sean on 2018/7/3
 *
 * @author Sean
 */
public class Test {
    public static void main(String[] args) throws UnknownHostException, SocketException {
        List<Demo1> demo1List=new ArrayList<>();
        demo1List.add(new Demo1("192.168.6.80","sss"));
        demo1List.add(new Demo1("192.168.6.78","timer"));
        List<String>    ipList= new ArrayList<>();
        try{
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            NetworkInterface networkInterface;
            Enumeration<InetAddress> inetAddresses;
            InetAddress inetAddress;
            String ip;
            while (networkInterfaces.hasMoreElements()) {
                networkInterface = networkInterfaces.nextElement();
                inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    inetAddress = inetAddresses.nextElement();
                    if (inetAddress != null && inetAddress instanceof Inet4Address) {
                        ip = inetAddress.getHostAddress();
                        ipList.add(ip);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        String collect = ipList.stream().map(s -> {
            String str="";
            for (Demo1 demo1 : demo1List) {
                if (demo1.getName().equals(s)){
                    str=s;
                }
            }
            return str;
        }).collect(Collectors.joining());
        List<Demo1> collect1 = demo1List.stream().filter(demo1 -> demo1.getName().equals(collect)).collect(Collectors.toList());
        System.out.println(collect1);

//        Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
//        InetAddress ip = null;
//        while (allNetInterfaces.hasMoreElements())
//        {
//            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
//            System.out.println(netInterface.getName());
//            Enumeration addresses = netInterface.getInetAddresses();
//            while (addresses.hasMoreElements())
//            {
//                ip = (InetAddress) addresses.nextElement();
//                if (ip != null && ip instanceof Inet4Address)
//                {
//                    System.out.println("本机的IP = " + ip.getHostAddress());
//                }
//            }
//        }


//        demo1List.stream().filter(demo1 -> demo1.getName().equals())
    }
}
