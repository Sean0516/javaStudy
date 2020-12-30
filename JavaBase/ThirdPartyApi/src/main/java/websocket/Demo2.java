package websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;

/**
 * @Description Demo2
 * @Author Sean
 * @Date 2020/11/17 15:06
 * @Version 1.0
 */
public class Demo2 {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public static void main(String[] args)  {
        Thread thread=new Thread(() -> {
            try {
                MsgWebSocketClient  msgWebSocketClient = new MsgWebSocketClient("ws://192.168.12.205:8080/test?type=rms_voice");
                msgWebSocketClient.connect();
                Thread.sleep(1000);
                msgWebSocketClient.send("11");
            } catch (URISyntaxException | InterruptedException e) {
                e.printStackTrace();
            }

        });
        thread.start();
    }
}
