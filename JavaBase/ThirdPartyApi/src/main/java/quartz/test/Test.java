package quartz.test;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import quartz.Test1Job;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author sean
 * @date 2019/8/19  14:20
 */
public class Test {
    public static void main(String[] args) throws SchedulerException {


//        List<LinkedBlockingQueue<String>> linkedBlockingQueues = new ArrayList<>(1);
//        LinkedBlockingQueue<String> uploadPathQueue1 = new LinkedBlockingQueue<>(1000);

        JobDetail test1 = JobBuilder.newJob(Test1Job.class)
                .build();
//        test1.getJobDataMap().put("uploadPathQueueList", linkedBlockingQueues);
        Trigger trigger1 = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0/30 0 11-12 * * ? *").withMisfireHandlingInstructionDoNothing()).build();

//        SimpleTrigger build1 = TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever())
//                .forJob(test1).build();

//        DailyTimeIntervalTrigger trigger2 = TriggerBuilder.newTrigger().withSchedule(DailyTimeIntervalScheduleBuilder.
//                dailyTimeIntervalSchedule().onEveryDay()
//                .startingDailyAt(new TimeOfDay(14, 40, 0)).endingDailyAt(new TimeOfDay(15, 0, 0)).withIntervalInSeconds(5)).build();

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(test1, trigger1);
//        scheduler.scheduleJob( build1);
//        scheduler.scheduleJob(test1, trigger2);
        scheduler.start();

//        linkedBlockingQueues.add(uploadPathQueue1);
//        TestJob testJob = new TestJob(uploadPathQueue1);
//        Thread thread = new Thread(testJob);
//        thread.setDaemon(true);
//        thread.start();



    }
}
