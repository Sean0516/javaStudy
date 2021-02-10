package websocket;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContextBuilder;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Description MsgWebSocketClient
 * @Author Sean
 * @Date 2020/11/17 11:33
 * @Version 1.0
 */
public class MsgWebSocketClient extends WebSocketClient {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public MsgWebSocketClient(String url) throws URISyntaxException {
        super(new URI(url));
    }


    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        logger.info("socket server open ");
    }

    @Override
    public void onMessage(String s) {
        logger.info(" socket server msg  [{}]",s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        logger.info("socket server close ");
    }

    @Override
    public void onError(Exception e) {
        logger.error("socket server error [{}] ",e.getMessage());
    }

    public static void main(String[] args) {
//        SSLContext sslContext = SSLContextBuilder.create().useProtocol(SSLConnectionSocketFactory.SSL).loadTrustMaterial((x, y) -> true).build();
//        SSLSocketFactory factory = sslContext
//                .getSocketFactory();// (SSLSocketFactory) SSLSocketFactory.getDefault();
//        appClient.setSocketFactory(factory);
    }
}
