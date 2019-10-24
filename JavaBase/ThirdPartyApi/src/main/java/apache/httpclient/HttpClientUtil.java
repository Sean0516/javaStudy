package apache.httpclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.dom4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sean on 2019/3/25
 *
 * @author Sean
 */
public class HttpClientUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);

    public String httpClientGet(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        LOGGER.info("start request url {} ", httpGet.getRequestLine());
        String str = HttpClientPoolUtil.getHttpClient().execute(httpGet, responseHandler());
        LOGGER.info("request return msg is {}", str);
        return str;
    }

    public String httpClientPostJson(String url, JSONObject object) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        String string = JSON.toJSONString(object);
        httpPost.setHeader("Content-Type", "application/json-rpc");
        httpPost.setHeader("Accept", "application/json-rpc");
        StringEntity stringEntity = new StringEntity(string, Charsets.UTF_8);
        httpPost.setEntity(stringEntity);
        LOGGER.info("start request url {} ", httpPost.getRequestLine());
        String execute = HttpClientPoolUtil.getHttpClient().execute(httpPost, responseHandler());
        LOGGER.info("request return msg is {}", execute);
        return execute;
    }

    public String httpClientPostForm(String url, String name) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> form = new ArrayList<>(2);
        form.add(new BasicNameValuePair("name", name));
        form.add(new BasicNameValuePair("sex", "男"));
        UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(form, Consts.UTF_8);
        httpPost.setEntity(encodedFormEntity);
        String execute = HttpClientPoolUtil.getHttpClient().execute(httpPost, responseHandler());
        return execute;
    }
// put  和delete 方法类似 。

    private ResponseHandler<String> responseHandler() {
        ResponseHandler<String> responseHandler = httpResponse -> {
            int status = httpResponse.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = httpResponse.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;

            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
        return responseHandler;
    }

    public void sendAudio(String url, boolean flag) throws IOException, InterruptedException {
        JSONObject object = new JSONObject();
        object.put("jsonrpc", "2.0");
        object.put("method", "deal_request");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "1");
        jsonObject.put("sid", "50000120191010144843");
        object.put("id", "50000120191010144843");
        if (flag) {
            jsonObject.put("cmd", "auw");
            List<File> fileList = new ArrayList<>();
            fileList.add(new File("D:\\50000120191010144843.mp3"));
//            fileList.add(new File("D:\\50000020191016101944_1.wav"));
            for (File file : fileList) {
                String name = file.getName();
                String substring = name.substring(name.lastIndexOf(".") + 1);
                jsonObject.put("postfix", substring);
                FileInputStream inputStream = new FileInputStream(file);
                byte[] bytes = new byte[1048576];
                while (inputStream.read(bytes) > 0) {
                    Base64 base64 = new Base64();
                    String data = base64.encodeToString(bytes);
                    jsonObject.put("data", data);
                    if (inputStream.available() <= 0) {
                        jsonObject.put("audioStatus", "4");
                    } else {
                        jsonObject.put("audioStatus", "2");
                    }

                    object.put("params", jsonObject);
                    String s = httpClientPostJson(url, object);
                    String decode = decode(s);

                    System.out.println("result ::: " + decode);
                }
            }
        } else {
            jsonObject.put("cmd", "grs");
            object.put("params", jsonObject);
            boolean type = true;
            long start = System.currentTimeMillis();
            while (type) {
                String s = httpClientPostJson(url, object);
                String decode = decode(s);
                System.out.println("get  result ::: " + decode);
                JSONObject result = JSONObject.parseObject(decode).getJSONObject("result");
                if (5 == result.getIntValue("recStatus")) {

                    type = false;
//                    String xmlResult = result.getString("result");
//                    Document document = DocumentHelper.parseText(xmlResult);
//                    Element text = (Element) document.selectSingleNode("//result/instance/subject[@value='search']/channel[@no='n0']/function[@value='1-best']/text");
//                    if (null != text) {
//                        System.out.println(text.getText());
//                    }
//                    Element time = (Element) document.selectSingleNode("//result/instance/subject[@value='search']/channel[@no='n0']/function[@value='1-best']/time");
//                    if (null != time) {
//                        System.out.println(time.getText());
//                    }
//
//                    Element text1 = (Element) document.selectSingleNode("//result/instance/subject[@value='search']/channel[@no='n1']/function[@value='1-best']/text");
//                    if (null != text) {
//                        System.out.println(text1.getText());
//                    }
//                    Element time1= (Element) document.selectSingleNode("//result/instance/subject[@value='search']/channel[@no='n1']/function[@value='1-best']/time");
//                    if (null != time) {
//                        System.out.println(time1.getText());
//                    }

                }else {
                    Thread.sleep(1000*10);
                }
            }
            long end = System.currentTimeMillis();
            long use = end - start;
            System.out.println("use seconds is : "+use / 1000);


        }

    }

    private static String decode(String s) {
        if (s == null) {
            return null;
        } else {
            BASE64Decoder decoder = new BASE64Decoder();

            try {
                byte[] b = decoder.decodeBuffer(s);
                return new String(b, "UTF-8");
            } catch (Exception var3) {
                return null;
            }
        }
    }

    private static void writeXmlFile(String xml) {

    }
}
