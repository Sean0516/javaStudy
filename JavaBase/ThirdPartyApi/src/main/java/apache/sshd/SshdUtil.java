package apache.sshd;

import org.apache.sshd.common.NamedFactory;
import org.apache.sshd.common.file.virtualfs.VirtualFileSystemFactory;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.auth.UserAuth;
import org.apache.sshd.server.auth.password.UserAuthPasswordFactory;
import org.apache.sshd.server.command.Command;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.scp.ScpCommandFactory;
import org.apache.sshd.server.subsystem.sftp.SftpSubsystemFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author sean
 * @date 2019/10/24  11:50
 */
public class SshdUtil {
    private ScheduledExecutorService scheduledExecutorService;

    public SshdUtil(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = scheduledExecutorService;
    }

    public SshServer initSftServer(String host, int port, Path filePath) {
        List<NamedFactory<UserAuth>> userAuthFactories = new ArrayList<>();
        userAuthFactories.add(new UserAuthPasswordFactory());
        SshServer sshServer = SshServer.setUpDefaultServer();
        sshServer.setHost(host);
        sshServer.setPort(port);
        sshServer.setKeyPairProvider(new SimpleGeneratorHostKeyProvider());
        sshServer.setUserAuthFactories(userAuthFactories);
        sshServer.setPasswordAuthenticator((s, s1, serverSession) -> {
            if ("root".equals(s) && "123456".equals(s1)) {
                return true;
            }
            return false;
        });
        ScpCommandFactory scpCommandFactory = new ScpCommandFactory();
        sshServer.setCommandFactory(scpCommandFactory);
        List<NamedFactory<Command>> namedFactoryList = new ArrayList<>();
        namedFactoryList.add(new SftpSubsystemFactory());
        sshServer.setSubsystemFactories(namedFactoryList);
        sshServer.setFileSystemFactory(new VirtualFileSystemFactory(filePath));
        sshServer.setScheduledExecutorService(scheduledExecutorService);
        return sshServer;
    }
}
