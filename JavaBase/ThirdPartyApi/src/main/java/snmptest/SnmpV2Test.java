package snmptest;

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
            Address address = GenericAddress.parse("udp:192.168.6.25/162");
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
            pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.5.0"),new OctetString("hello")));
//            ResponseListener listener = new ResponseListener() {
//                public void onResponse(ResponseEvent event) {
//                    ((Snmp) event.getSource()).cancel(event.getRequest(), this);
//                    PDU response = event.getResponse();
//                    if (response == null) {
//                        System.out.println("connect time out");
//                    } else {
//                        System.out.println("Received response " +response);
//                    }
//                }
//            };null, listener
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
