package quartz.test;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import quartz.TestUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author sean
 * @date 2019/8/15  16:25
 */
public class TestJob implements Runnable {
    private LinkedBlockingQueue<String> uploadPathQueue;

    public TestJob(LinkedBlockingQueue<String> uploadPathQueue) {
        this.uploadPathQueue = uploadPathQueue;
    }

    @Override
    public void run() {
        System.out.println("start TestJob");
//        uploadPathQueue = (LinkedBlockingQueue<String>) jobExecutionContext.getJobDetail().getJobDataMap().get("uploadPathQueue");
        while (true) {
//            LocalDate localDate=LocalDate.now();
//            LocalDateTime localDateTime = localDate.atTime(10, 28, 0);
//            if (localDateTime.isBefore(LocalDateTime.now())){
//                System.out.println("当前时间大于定时任务时间，线程停止");
//                break;
//            }else {

//            }
            try {
                System.out.println("uploadPathQueue size is "+uploadPathQueue.size());
                String take = uploadPathQueue.take();
                System.out.println("get from uploadPathQueue is  "+ take);
                Thread.sleep(10*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
