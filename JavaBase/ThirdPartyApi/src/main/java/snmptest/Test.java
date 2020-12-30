package snmptest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description Test
 * @Author Sean
 * @Date 2020/8/7 16:17
 * @Version 1.0
 */
public class Test {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {

        JSONArray jsonArray=new JSONArray();

        JSONObject object=new JSONObject();
        object.put("type","cpu");
        object.put("oid","1.3.6.1.4.1.8099.11.1");
        jsonArray.add(object);


        JSONObject memory=new JSONObject();
        memory.put("type","memory");
        memory.put("oid","1.3.6.1.4.1.8099.11.2");
        jsonArray.add(memory);

        JSONObject disk=new JSONObject();
        disk.put("type","disk");
        disk.put("oid","1.3.6.1.4.1.8099.11.3");
        jsonArray.add(disk);


        JSONObject zeroCall=new JSONObject();
        zeroCall.put("type","zeroCall");
        zeroCall.put("oid","1.3.6.1.4.1.8099.12.1");
        jsonArray.add(zeroCall);

        JSONObject shortCall=new JSONObject();
        shortCall.put("type","shortCall");
        shortCall.put("oid","1.3.6.1.4.1.8099.12.2");
        jsonArray.add(shortCall);

        JSONObject longCall=new JSONObject();
        longCall.put("type","longCall");
        longCall.put("oid","1.3.6.1.4.1.8099.12.3");
        jsonArray.add(longCall);
        JSONObject licenseConnect=new JSONObject();
        licenseConnect.put("type","licenseConnect");
        licenseConnect.put("oid","1.3.6.1.4.1.8099.15.1");
        licenseConnect.put("status",0);
        jsonArray.add(licenseConnect);

        JSONObject licenseConnect2=new JSONObject();
        licenseConnect2.put("type","licenseConnect");
        licenseConnect2.put("oid","1.3.6.1.4.1.8099.15.2");
        licenseConnect2.put("status",1);
        jsonArray.add(licenseConnect2);

        JSONObject process=new JSONObject();
        process.put("type","service");
        process.put("oid","1.3.6.1.4.1.8099.16.1");
        process.put("status",0);
        jsonArray.add(process);

        JSONObject process2=new JSONObject();
        process2.put("type","service");
        process2.put("oid","1.3.6.1.4.1.8099.16.2");
        process2.put("status",1);
        jsonArray.add(process2);

        System.out.println(jsonArray.toString());









    }
}
