package quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author sean
 * @date 2019/8/15  16:25
 */
public class TestJob implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("start TestJob");
//        TestUtil testUtil = (TestUtil) jobExecutionContext.getJobDetail().getJobDataMap().get("util");
//        String name = jobExecutionContext.getJobDetail().getJobDataMap().getString("name");
//        LinkedBlockingQueue<String> uploadPathQueue = (LinkedBlockingQueue<String>) jobExecutionContext.getJobDetail().getJobDataMap().get("uploadPathQueue");
        while (true) {
            System.out.println("线程继续");
            LocalDate localDate=LocalDate.now();
            LocalDateTime localDateTime = localDate.atTime(12, 02, 0);
            if (localDateTime.isBefore(LocalDateTime.now())){
                System.out.println("当前时间大于定时任务时间，线程停止");
                break;
            }else {
                try {
                    Thread.sleep(10*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
        System.out.println("线程停止");
    }


}
