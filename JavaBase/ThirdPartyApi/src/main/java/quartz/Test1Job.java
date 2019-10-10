package quartz;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import org.quartz.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author sean
 * @date 2019/8/15  16:34
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class Test1Job implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("生产者向 uploadPathQueue 中添加数据" + new Date());
//        List<LinkedBlockingQueue<String>> uploadPathQueueList = (List<LinkedBlockingQueue<String>>) jobExecutionContext.getJobDetail().getJobDataMap().get("uploadPathQueueList");
//        if (uploadPathQueueList != null && !uploadPathQueueList.isEmpty()) {
//            for (LinkedBlockingQueue<String> strings : uploadPathQueueList) {
//                try {
//                    System.out.println("开始 put 数据 "+new Date() );
//                    for (int i = 0; i <100 ; i++) {
//                        strings.put("test" + UUID.randomUUID());
//                    }
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
////                    System.out.println("休眠结束 "+new Date());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }
}
