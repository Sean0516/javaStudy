package apache.exec;

import com.google.common.io.Files;
import org.apache.commons.exec.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author sean
 * @date 2019/12/13  18:07
 */
public class ExecTest {
    public static void main(String[] args) throws IOException {
        CommandLine commandLine=CommandLine.parse("ping www.baidu.com");
        //接收正常结果流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //接收异常结果流
        ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
        PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(outputStream,errorStream);
        DefaultExecutor executor=new DefaultExecutor();
        executor.setStreamHandler(pumpStreamHandler);
        ExecuteWatchdog watchdog = new ExecuteWatchdog(10*1000);
        executor.setWatchdog(watchdog);
        int execute = executor.execute(commandLine);
        System.out.println(execute);
        String outMsg = outputStream.toString("GBK");
        String errorMsg = errorStream.toString("GBK");
        System.out.println("out "+outMsg);
        System.out.println("error "+errorMsg);
        outputStream.close();
        errorStream.close();
    }
}
