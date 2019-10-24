package apache.sshd;


import org.apache.sshd.server.SshServer;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author sean
 * @date 2019/10/23  17:01
 */
public class Test {
    public static void main(String[] args) throws IOException {
        ScheduledExecutorService scheduledExecutorService=new ScheduledThreadPoolExecutor(3);
        SshdUtil sshdUtil=new SshdUtil(scheduledExecutorService);
        SshServer sshServer = sshdUtil.initSftServer("192.168.6.25", 22, Paths.get("D:\\sftp"));
        sshServer.start();

    }



}
