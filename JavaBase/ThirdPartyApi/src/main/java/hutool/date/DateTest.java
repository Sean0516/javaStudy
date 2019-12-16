package hutool.date;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author sean
 * @date 2019/12/16  15:55
 */
public class DateTest {
    public static void main(String[] args) {
        DateTime date = DateUtil.date();
        DateTime parse = DateUtil.parse("2219-12-2");
        DateUtil.formatDate(date);
        DateUtil.formatTime(date);
        DateUtil.beginOfDay(date);
        DateUtil.endOfDay(date);
        DateUtil.yesterday();

    }
}
