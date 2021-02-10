package snmptest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.checkerframework.checker.units.qual.C;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;
import java.util.Vector;

public class SnmpV2Test {
    public static void main(String[] args) {

        Snmp snmp = null;
        TransportMapping<UdpAddress> transport = null;
        try {
            Address address = GenericAddress.parse("udp:192.168.12.201/161");
            transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            transport.listen();
            CommunityTarget communityTarget = new CommunityTarget();
            communityTarget.setVersion(SnmpConstants.version2c);
            communityTarget.setAddress(address);
            communityTarget.setCommunity(new OctetString("public"));
            communityTarget.setTimeout(10000);
            PDU pdu = new PDU();
            pdu.setType(PDU.TRAP);
            JSONObject object=new JSONObject();
            object.put("ip","127.0.0.1");
            object.put("used",20);
            object.put("timestamp",System.currentTimeMillis());
            pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.8099.11.1"),new OctetString(JSON.toJSONString(object))));
            snmp.send(pdu, communityTarget);

            object.put("used",40);
            object.put("timestamp",System.currentTimeMillis());
            pdu.clear();
            pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.8099.11.2"),new OctetString(JSON.toJSONString(object))));
            snmp.send(pdu, communityTarget);

            object.remove("used");
            object.put("left",64);
            object.put("timestamp",System.currentTimeMillis());
            pdu.clear();
            pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.8099.11.3"),new OctetString(JSON.toJSONString(object))));
            snmp.send(pdu, communityTarget);
            pdu.clear();
            object.remove("left");
            object.put("licenseServer","192.168.0.1");
            object.put("timestamp",System.currentTimeMillis());
            pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.8099.15.1"),new OctetString(JSON.toJSONString(object))));
            snmp.send(pdu, communityTarget);
            pdu.clear();
            pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.8099.15.2"),new OctetString(JSON.toJSONString(object))));
            snmp.send(pdu, communityTarget);
            pdu.clear();

            object.remove("licenseServer");
            object.put("num",2);
            object.put("timestamp",System.currentTimeMillis());
            pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.8099.15.3"),new OctetString(JSON.toJSONString(object))));
            snmp.send(pdu, communityTarget);
            pdu.clear();

            object.put("num",100);
            object.put("timestamp",System.currentTimeMillis());
            pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.8099.15.4"),new OctetString(JSON.toJSONString(object))));
            snmp.send(pdu, communityTarget);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != snmp) {
                    snmp.close();
                }
                if (null != transport) {
                    transport.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
}
