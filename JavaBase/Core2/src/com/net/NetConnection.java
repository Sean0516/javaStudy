package com.net;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;
import java.util.List;
import java.util.Map;

/**
 * Created by Sean on 2018/5/28.
 */
public class NetConnection {
    public static void main(String[] args) throws IOException {
        URL url=new URL("https://www.baidu.com/");
        URLConnection connection= url.openConnection();
        Map<String, List<String>> headerFields = connection.getHeaderFields();
//        InputStream inputStream = connection.getInputStream();
//        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
//        String str;
//        while ((str=bufferedReader.readLine())!=null){
//            System.out.println(str);
//        }
    }
}
