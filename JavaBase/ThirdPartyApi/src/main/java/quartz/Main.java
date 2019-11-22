package quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author sean
 * @date 2019/8/15  16:29
 */
public class Main {
    public static void main(String[] args) throws SchedulerException {
//        LinkedBlockingQueue<String> uploadPathQueue1 = new LinkedBlockingQueue<>(1000);
//        JobDetail build = JobBuilder.newJob(TestJob.class)
//                .build();
//        TestUtil testUtil = new TestUtil("sean");
//        build.getJobDataMap().put("uploadPathQueue", uploadPathQueue1);
//        build.getJobDataMap().put("util", testUtil);
//        build.getJobDataMap().put("name", "Sean");

//        LinkedBlockingQueue<String> uploadPathQueue2 = new LinkedBlockingQueue<>(1000);
//        JobDetail build2 = JobBuilder.newJob(TestJob.class)
//                .build();
//        build2.getJobDataMap().put("uploadPathQueue", uploadPathQueue2);
//        TestUtil testUtil2 = new TestUtil("MASTER");
//        build2.getJobDataMap().put("util", testUtil2);
//        build2.getJobDataMap().put("name", "Master");

//        Trigger trigger1 = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0 26 10 * * ?")).build();
//        SimpleTrigger build1 = TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever())
//                .forJob(build).build();
//        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//        scheduler.scheduleJob(build, trigger1);
//        scheduler.scheduleJob(build1);
////        scheduler.scheduleJob(test1, trigger2);
//        scheduler.start();


//        List<LinkedBlockingQueue<String>> linkedBlockingQueues = new ArrayList<>(2);
//        linkedBlockingQueues.add(uploadPathQueue1);
//        linkedBlockingQueues.add(uploadPathQueue2);


//        JobDetail test1 = JobBuilder.newJob(Test1Job.class)
//                .build();
//        test1.getJobDataMap().put("uploadPathQueueList", linkedBlockingQueues);
//        Trigger trigger2 = TriggerBuilder.newTrigger()
//                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever())
//                .build();
//        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//        JobDetail test1 = JobBuilder.newJob(TestJob.class)
//                .build();
//        DailyTimeIntervalTrigger trigger2 = TriggerBuilder.newTrigger().withSchedule(DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
//                .startingDailyAt(new TimeOfDay(12, 00, 50))
//        ).build();
//        scheduler.scheduleJob(test1,trigger2);
//        scheduler.start();

    }
}
