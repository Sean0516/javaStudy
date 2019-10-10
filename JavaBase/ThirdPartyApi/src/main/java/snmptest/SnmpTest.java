package snmptest;

import org.snmp4j.*;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.MPv3;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.*;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;
import java.net.SocketException;
import java.net.URLClassLoader;
import java.util.Vector;

/**
 * Created by Sean on 2019/3/20
 *
 * @author Sean
 */
public class SnmpTest {
    public static void main(String[] args) throws IOException {
        Address address = GenericAddress.parse("udp:192.168.6.25/162");
        TransportMapping<UdpAddress> transport = new DefaultUdpTransportMapping();
        Snmp snmp = new Snmp(transport);
        transport.listen();
        SNMP4JSettings.setExtensibilityEnabled(true);
        SecurityProtocols.getInstance().addDefaultProtocols();
        UserTarget target = new UserTarget();
        target.setVersion(SnmpConstants.version3);
        target.setRetries(2);
        target.setTimeout(5000);
        byte[] localEngineID = MPv3.createLocalEngineID();
        String engineId=new String(localEngineID);
        USM usm = new USM(SecurityProtocols.getInstance(), new OctetString(
                localEngineID), 500);
        SecurityModels secModels = SecurityModels.getInstance();
        if (snmp.getUSM() == null) {
            secModels.addSecurityModel(usm);
        }
        snmp.getUSM().addUser(new OctetString("sean"),new UsmUser(new OctetString("sean"),AuthMD5.ID,new OctetString("duplicall"),PrivDES.ID,new OctetString("duplicall")));
        target.setSecurityLevel(SecurityLevel.NOAUTH_NOPRIV);
        target.setAddress(address);
        target.setSecurityName(new OctetString("sean"));
        ScopedPDU pdu = new ScopedPDU();
        pdu.setType(PDU.TRAP);
        VariableBinding v = new VariableBinding();
        v.setOid(new OID(".1.3.6.1.2.1.1.5.0.2.2"));
        v.setVariable(new OctetString("abcsdsas"));
        pdu.add(v);
        ResponseEvent send = snmp.send(pdu, target);
        if (null!=send&&send.getResponse()!=null){
            System.out.println(send.getResponse());
        }
        snmp.close();
        transport.close();
    }


}
