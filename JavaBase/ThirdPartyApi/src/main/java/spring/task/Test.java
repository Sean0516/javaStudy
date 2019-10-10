package spring.task;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author sean
 * @date 2019/8/19  10:38
 */
public class Test {
    public static void main(String[] args) {
        ThreadPoolTaskScheduler  taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        taskScheduler.setAwaitTerminationSeconds(60);
        taskScheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        taskScheduler.initialize();
        taskScheduler.schedule(new TestThread(),new CronTrigger("0/5 55 15,16 * * ?"));
    }
}
