package spring.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author sean
 * @date 2019/8/19  10:41
 */
public class TestThread implements Runnable {
    @Override
    public void run() {
        System.out.println("线程开始");
        while (true){
            LocalDate localDate=LocalDate.now();
            LocalDateTime localDateTime = localDate.atTime(20, 47, 0);
            if (localDateTime.isBefore(LocalDateTime.now())){
                System.out.println("当前时间大于定时任务时间，线程停止");
                break;
            }else {
                System.out.println("TestThread run " + new Date());
                try {
                    Thread.sleep(10*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程停止结束" + new Date());
            }
        }
    }
}
