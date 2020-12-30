package totp;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * @Description Test
 * @Author Sean
 * @Date 2020/12/21 16:42
 * @Version 1.0
 */
public class Test {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        Date date=new Date();
        String ddMMyy = DateFormatUtils.format(date, "ddMMyy");
        System.out.println(ddMMyy);
    }
}
