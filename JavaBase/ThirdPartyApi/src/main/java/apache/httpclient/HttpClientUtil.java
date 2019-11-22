package apache.httpclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
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
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                return entity != null ? EntityUtils.toString(entity,"UTF-8") : null;

            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
        return responseHandler;
    }




}
