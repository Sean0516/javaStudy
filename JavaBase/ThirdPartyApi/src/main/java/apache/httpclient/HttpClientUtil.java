package apache.httpclient;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
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
    private static HttpClientUtil httpClientUtil = null;
    private static CloseableHttpClient httpClient = null;

    private HttpClientUtil() {

    }

    public static HttpClientUtil getInstance() {
        if (httpClientUtil == null || httpClient == null) {
            HttpClientPoolUtil httpClientPoolUtil = new HttpClientPoolUtil();
            httpClient = httpClientPoolUtil.getHttpClient();
            httpClientUtil = new HttpClientUtil();
        }
        return httpClientUtil;
    }

    public String httpClientGet(String url) throws IOException {

        HttpGet httpGet = new HttpGet(url);
        LOGGER.info("start request url {} ", httpGet.getRequestLine());
        String str = httpClient.execute(httpGet, responseHandler());
        LOGGER.info("request return msg is {}", str);
        return str;
    }

    public String httpClientPostJson(String url, String name) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        Map<String, Object> map = new HashMap<>(2);
        map.put("name", name);
        map.put("age", 23);
        String string = JSON.toJSONString(map);
        System.out.println(string);
        httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
        httpPost.setHeader("Accept", "application/json");
        StringEntity stringEntity = new StringEntity(string, Charsets.UTF_8);
        httpPost.setEntity(stringEntity);
        LOGGER.info("start request url {} ", httpPost.getRequestLine());
        String execute = httpClient.execute(httpPost, responseHandler());
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
        String execute = httpClient.execute(httpPost, responseHandler());
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
}
