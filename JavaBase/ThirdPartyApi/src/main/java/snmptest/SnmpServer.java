package snmptest;

import org.snmp4j.*;
import org.snmp4j.mp.*;
import org.snmp4j.security.*;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultTcpTransportMapping;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.MultiThreadedMessageDispatcher;
import org.snmp4j.util.ThreadPool;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

public class SnmpServer implements CommandResponder {
    private MultiThreadedMessageDispatcher dispatcher;
    private Snmp snmp = null;
    private Address listenAddress;
    private ThreadPool threadPool;
    TransportMapping transport;

    private void init() {

        try {
            threadPool = ThreadPool.create("Trap", 5);
            dispatcher = new MultiThreadedMessageDispatcher(threadPool,
                    new MessageDispatcherImpl());
            listenAddress = GenericAddress.parse(System.getProperty(
                    "snmp4j.listenAddress", "udp:0.0.0.0/161"));

            // 对TCP与UDP协议进行处理
            if (listenAddress instanceof UdpAddress) {
                transport = new DefaultUdpTransportMapping(
                        (UdpAddress) listenAddress);
            } else {
                transport = new DefaultTcpTransportMapping(
                        (TcpAddress) listenAddress);
            }
            snmp = new Snmp(dispatcher, transport);
            snmp.getMessageDispatcher().addMessageProcessingModel(new MPv1());
            snmp.getMessageDispatcher().addMessageProcessingModel(new MPv2c());
            snmp.getMessageDispatcher().addMessageProcessingModel(new MPv3());
            USM usm = new USM(SecurityProtocols.getInstance(), new OctetString(MPv3
                    .createLocalEngineID()), 0);
            SecurityModels.getInstance().addSecurityModel(usm);
            UsmUser usmUser = new UsmUser(new OctetString("CLog"), null, null, null, null);
            UsmUserEntry usmUserEntry = new UsmUserEntry(new OctetString("CLog"), usmUser);
            snmp.getUSM().getUserTable().addUser(usmUserEntry);
            snmp.listen();
            snmp.addCommandResponder(this);
            System.out.println("start monitor ");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void processPdu(CommandResponderEvent respEvnt) {
        if (respEvnt != null && respEvnt.getPDU() != null) {
            PDU pdu = respEvnt.getPDU();
            System.out.println(respEvnt.getPDU().toString());
            if (pdu.getType() == PDU.GET) {
                System.out.println("get GET msg ,response msg to client ");
                pdu.setType(PDU.RESPONSE);
                System.out.println("security level " + respEvnt.getSecurityLevel() + "security name " + new String(respEvnt.getSecurityName(), StandardCharsets.UTF_8));
                System.out.println("oid "+ pdu.get(0).getOid().toDottedString());
                pdu.get(0).setVariable(new OctetString("hello client" ));
                StatusInformation statusInformation = new StatusInformation();
                StateReference stateReference = respEvnt.getStateReference();
                try {
                    respEvnt.getMessageDispatcher().returnResponsePdu(

                            respEvnt.getMessageProcessingModel(),

                            respEvnt.getSecurityModel(), respEvnt.getSecurityName(),

                            respEvnt.getSecurityLevel(), pdu,

                            respEvnt.getMaxSizeResponsePDU(), stateReference,

                            statusInformation);
                } catch (MessageException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) {
        SnmpServer snmpServer = new SnmpServer();
        snmpServer.init();
    }
}
